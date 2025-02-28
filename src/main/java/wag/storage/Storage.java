package wag.storage;

import wag.tasks.Deadline;
import wag.tasks.Event;
import wag.tasks.Task;
import wag.tasks.Todo;

import java.io.*;


public class Storage {
    private static final String FILE_PATH = "data" + File.separator + "wag.txt";

    /**
     * Loads wag.tasks from the file. If the file or its directory does not exist,
     * they are created and an empty wag.tasks array is returned.
     */
    public static Task[] loadFile() {
        Task[] tasks = new Task[100];
        int taskCount = 0;
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
            return tasks; // Return empty array
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Each line should be in the format: Type | status | description | (others)
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Corrupted line (skipped): " + line);
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length < 4) {
                        System.out.println("Corrupted deadline line (skipped): " + line);
                        continue;
                    }
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    if (parts.length < 5) {
                        System.out.println("Corrupted event line (skipped): " + line);
                        continue;
                    }
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    System.out.println("Unknown task type (skipped): " + line);
                    continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks[taskCount++] = task;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current wag.tasks to the file.
     * @param tasks an array containing wag.tasks.
     * @param taskCount the number of valid wag.tasks in the array.
     */
    public static void save(Task[] tasks, int taskCount) {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks[i];
                String line = "";
                if (task instanceof Todo) {
                    line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();  // <-- Use getters
                } else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    line = "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + d.getBy();  // <-- Use getters
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    line = "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + e.getFrom() + " | " + e.getTo();  // <-- Use getters
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

}
