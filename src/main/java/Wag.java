import java.util.Scanner;

public class Wag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "\n" +
                " __      __                 \n" +
                "/  \\    /  \\_____     ____  \n" +
                "\\   \\/\\/   /\\__  \\   / ___\\ \n" +
                " \\        /  / __ \\_/ /_/  >\n" +
                "  \\__/\\  /  (____  /\\___  / \n" +
                "       \\/        \\/_____/  \n";
        System.out.println(logo);
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm Wag");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________");

        // Load tasks from the hard disk at startup
        Task[] tasks = Storage.load();
        int taskCount = 0;
        for (Task task : tasks) {
            if (task != null) {
                taskCount++;
            }
        }

        String command;

        while (true) {
            try {
                command = scanner.nextLine();
                System.out.println("_______________________________________");

                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_______________________________________");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    if (taskCount == 0) {
                        System.out.println("No tasks added yet.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                    }
                } else if (command.startsWith("mark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        throw new WagException("Please provide a valid task number to mark as done.");
                    }
                    int taskId = Integer.parseInt(parts[1]) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new WagException("Task number is out of range.");
                    }
                    tasks[taskId].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskId]);
                    // Save changes
                    Storage.save(tasks, taskCount);
                } else if (command.startsWith("unmark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        throw new WagException("Please provide a valid task number to unmark.");
                    }
                    int taskId = Integer.parseInt(parts[1]) - 1;
                    if (taskId < 0 || taskId >= taskCount) {
                        throw new WagException("Task number is out of range.");
                    }
                    tasks[taskId].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskId]);
                    // Save changes
                    Storage.save(tasks, taskCount);
                } else if (command.startsWith("todo")) {
                    String taskDescription = command.substring(5).trim();
                    if (taskDescription.isEmpty()) {
                        throw new WagException("The description for a todo cannot be empty.");
                    }
                    tasks[taskCount] = new Todo(taskDescription);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    // Save changes
                    Storage.save(tasks, taskCount);
                } else if (command.startsWith("deadline")) {
                    String details = command.substring(9).trim();
                    if (details.isEmpty() || !details.contains(" /by ")) {
                        throw new WagException("Deadline command must be in the format: deadline <description> /by <date>.");
                    }
                    String[] parts = details.split(" /by ", 2);
                    if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new WagException("The deadline description or date cannot be empty.");
                    }
                    tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    // Save changes
                    Storage.save(tasks, taskCount);
                } else if (command.startsWith("event")) {
                    String details = command.substring(6).trim();
                    if (details.isEmpty() || !details.contains(" /from ") || !details.contains(" /to ")) {
                        throw new WagException("Event command must be in the format: event <description> /from <start> /to <end>.");
                    }
                    String[] parts = details.split(" /from | /to ", 3);
                    if (parts.length < 3 || parts[0].trim().isEmpty() ||
                            parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new WagException("The event description, start, and end times cannot be empty.");
                    }
                    tasks[taskCount] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    // Save changes
                    Storage.save(tasks, taskCount);
                } else {
                    throw new WagException("I'm sorry, but I don't know what that means.");
                }
                System.out.println("_______________________________________");
            } catch (WagException e) {
                WagErrorHandler.handleError(e);
            } catch (NumberFormatException e) {
                WagErrorHandler.handleError(new WagException("Task number should be a valid integer."));
            } catch (Exception e) {
                WagErrorHandler.handleError(new WagException("An unexpected error occurred."));
            }
        }
    }
}
