import java.util.Scanner;

// Abstract class to represent a generic task
abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    // Override toString to return the task status and description
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

// Class for Todo tasks
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // Override toString to represent Todo tasks with a [T] icon
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

// Class for Deadline tasks
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // Override toString to represent Deadline tasks with a [D] icon and deadline date
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

// Class for Event tasks
class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    // Override toString to represent Event tasks with a [E] icon and time range
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

// Main class for the Wag Program
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

        Task[] tasks = new Task[100];
        int taskCount = 0;
        String command;

        while (true) {
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
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskId >= 0 && taskId < taskCount) {
                        tasks[taskId].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[taskId]);
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
                        tasks[taskId].unmarkAsDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[taskId]);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format. Please use 'unmark <task_number>'.");
                }
            } else if (command.startsWith("todo")) {
                String taskDescription = command.substring(5);
                tasks[taskCount] = new Todo(taskDescription);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
            } else if (command.startsWith("deadline")) {
                String[] parts = command.substring(9).split(" /by ", 2);
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
            } else if (command.startsWith("event")) {
                String[] parts = command.substring(6).split(" /from | /to ", 3);
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
            } else {
                System.out.println("Invalid command.");
            }
            System.out.println("_______________________________________");
        }
    }
}