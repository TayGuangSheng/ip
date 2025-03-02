package wag.storage;

import wag.tasks.Deadline;
import wag.tasks.Event;
import wag.tasks.Task;
import wag.tasks.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The {@code Storage} class handles loading and saving tasks to a persistent file.
 */
public class Storage {
    private static final String FILE_PATH = "data" + File.separator + "wag.txt";
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Loads tasks from the file. If the file or its directory does not exist,
     * they are created, and an empty task list is returned.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     */
    public static ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();

        // Ensure parent directory exists
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        // Create file if it does not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return tasks; // Return empty task list
        }

        // Read tasks from the file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");

                // Validate line format
                if (parts.length < 3) {
                    System.out.println("Corrupted line (skipped): " + line);
                    continue;
                }

                Task task = null;
                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        try {
                            LocalDateTime deadlineTime = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                            task = new Deadline(parts[2], deadlineTime);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error parsing deadline date: " + parts[3]);
                        }
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        try {
                            LocalDateTime fromTime = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                            LocalDateTime toTime = LocalDateTime.parse(parts[4], STORAGE_FORMAT);
                            task = new Event(parts[2], fromTime, toTime);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error parsing event dates: " + parts[3] + ", " + parts[4]);
                        }
                    }
                    break;
                }

                if (task == null) {
                    System.out.println("Corrupted task (skipped): " + line);
                    continue;
                }

                // Mark task as done if indicated
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
     *
     * @param tasks An {@code ArrayList} containing the tasks to be saved.
     */
    public static void save(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();

        // Ensure parent directory exists
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        // Write tasks to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = "";
                switch (task.getClass().getSimpleName()) {
                case "Todo":
                    line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    break;
                case "Deadline":
                    Deadline d = (Deadline) task;
                    line = "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " +
                            d.getStorageFormat();
                    break;
                case "Event":
                    Event e = (Event) task;
                    line = "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " +
                            e.getStorageFrom() + " | " + e.getStorageTo();
                    break;
                }
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
