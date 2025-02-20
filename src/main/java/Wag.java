import java.util.ArrayList;
import java.util.Scanner;

public class Wag {
    public Wag() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "\n __      __                 \n/  \\    /  \\_____     ____  \n\\   \\/\\/   /\\__  \\   / ___\\ \n \\        /  / __ \\_/ /_/  >\n  \\__/\\  /  (____  /\\___  / \n       \\/        \\/_____/  \n";
        System.out.println(logo);
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm Wag");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________");

        // Use an ArrayList to store tasks dynamically
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            try {
                String command = scanner.nextLine();
                System.out.println("_______________________________________");

                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_______________________________________");
                    return;
                }

                if (command.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks added yet.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                } else if (command.startsWith("mark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        throw new WagException("Please provide a valid task number to mark as done.");
                    }

                    int taskId = Integer.parseInt(parts[1]) - 1;
                    if (taskId < 0 || taskId >= tasks.size()) {
                        throw new WagException("Task number is out of range.");
                    }

                    tasks.get(taskId).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskId));
                } else if (command.startsWith("unmark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        throw new WagException("Please provide a valid task number to unmark.");
                    }

                    int taskId = Integer.parseInt(parts[1]) - 1;
                    if (taskId < 0 || taskId >= tasks.size()) {
                        throw new WagException("Task number is out of range.");
                    }

                    tasks.get(taskId).unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskId));
                } else if (command.startsWith("todo")) {
                    String taskDescription = command.substring(5).trim();
                    if (taskDescription.isEmpty()) {
                        throw new WagException("The description for a todo cannot be empty.");
                    }

                    tasks.add(new Todo(taskDescription));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                } else if (command.startsWith("deadline")) {
                    String details = command.substring(9).trim();
                    if (!details.isEmpty() && details.contains(" /by ")) {
                        String[] parts = details.split(" /by ", 2);
                        if (!parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                        } else {
                            throw new WagException("The deadline description or date cannot be empty.");
                        }
                    } else {
                        throw new WagException("Deadline command must be in the format: deadline <description> /by <date>.");
                    }
                } else if (command.startsWith("event")) {
                    String details = command.substring(6).trim();
                    if (!details.isEmpty() && details.contains(" /from ") && details.contains(" /to ")) {
                        String[] parts = details.split(" /from | /to ", 3);
                        if (parts.length >= 3 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty() && !parts[2].trim().isEmpty()) {
                            tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                        } else {
                            throw new WagException("The event description, start, and end times cannot be empty.");
                        }
                    } else {
                        throw new WagException("Event command must be in the format: event <description> /from <start> /to <end>.");
                    }
                } else if (command.startsWith("delete")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        throw new WagException("Please provide a valid task number to delete.");
                    }

                    int taskId = Integer.parseInt(parts[1]) - 1;
                    if (taskId < 0 || taskId >= tasks.size()) {
                        throw new WagException("Task number is out of range.");
                    }

                    // Get the task to delete
                    Task taskToDelete = tasks.get(taskId);
                    tasks.remove(taskId); // Remove the task

                    // Output the success message
                    System.out.println("_______________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + taskToDelete);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("_______________________________________");
                } else {
                    throw new WagException("I'm sorry, but I don't know what that means.");
                }

                System.out.println("_______________________________________");
            } catch (WagException e) {
                WagErrorHandler.handleError(e);
            } catch (NumberFormatException var9) {
                WagErrorHandler.handleError(new WagException("Task number should be a valid integer."));
            } catch (Exception var10) {
                WagErrorHandler.handleError(new WagException("An unexpected error occurred."));
            }
        }
    }
}
