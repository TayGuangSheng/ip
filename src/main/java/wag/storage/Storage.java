package wag.storage;

import wag.tasks.Deadline;
import wag.tasks.Event;
import wag.tasks.Task;
import wag.tasks.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "data" + File.separator + "wag.txt";

    /**
     * Loads tasks from the file. If the file or its directory does not exist,
     * they are created and an empty task list is returned.
     */
    public static ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return tasks; // Return empty ArrayList
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Corrupted line (skipped): " + line);
                    continue;
                }

                Task task = switch (parts[0]) {
                case "T"  -> new Todo(parts[2]);
                case "D"  -> (parts.length < 4) ? null : new Deadline(parts[2], parts[3]);
                case "E"  -> (parts.length < 5) ? null : new Event(parts[2], parts[3], parts[4]);
                default   -> null;
                };

                if (task == null) {
                    System.out.println("Corrupted task (skipped): " + line);
                    continue;
                }

                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current tasks to the file.
     * @param tasks an ArrayList containing tasks.
     */
    public static void save(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = switch (task.getClass().getSimpleName()) {
                    case "Todo"     -> "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    case "Deadline" -> {
                        Deadline d = (Deadline) task;
                        yield "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + d.getBy();
                    }
                    case "Event"    -> {
                        Event e = (Event) task;
                        yield "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
                    }
                    default         -> "";
                };
                if (!line.isEmpty()) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
