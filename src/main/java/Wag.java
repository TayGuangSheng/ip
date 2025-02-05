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
                "       \\/        \\//_____/  \n";

        System.out.println(logo);
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm Wag");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________");

        String[] tasks = new String[100];  // Store up to 100 tasks
        boolean[] taskStatus = new boolean[100];  // Store task completion status
        int taskCount = 0;  // Count of tasks added
        String command = "";

        // Loop to handle user input and echo the command until "bye" is typed
        while (!command.equalsIgnoreCase("bye")) {
            command = scanner.nextLine();  // Get user input
            System.out.println("_______________________________________");

            if (command.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        String status = taskStatus[i] ? "[X]" : "[ ]";  // Mark task as done or not
                        System.out.println((i + 1) + ". " + status + " " + tasks[i]);
                    }
                }
            } else if (command.startsWith("mark")) {
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskId >= 0 && taskId < taskCount) {
                        taskStatus[taskId] = true;  // Mark task as done
                        System.out.println("Nice! I've marked this task as done:\n  [X] " + tasks[taskId]);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format. Please use 'mark <task_number>'.");
                }
            } else if (command.startsWith("unmark")) {
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskId >= 0 && taskId < taskCount) {
                        taskStatus[taskId] = false;  // Mark task as not done
                        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + tasks[taskId]);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format. Please use 'unmark <task_number>'.");
                }
            } else if (!command.equalsIgnoreCase("bye")) {
                tasks[taskCount] = command;  // Add task to the list
                taskStatus[taskCount] = false;  // Default status is not done
                taskCount++;  // Increment task count
                System.out.println("added: " + command);
            }

            System.out.println("_______________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }
}
