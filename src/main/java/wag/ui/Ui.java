package wag.ui;

import wag.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions by printing messages to the console
 * and reading user input.
 */
public class Ui {
    private static final String SEPARATOR = "________________________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when the program starts.
     */
    public void printWelcomeMessage() {
        System.out.println(" __      __                 ");
        System.out.println("/  \\    /  \\_____     ____  ");
        System.out.println("\\   \\/\\/   /\\__  \\   / ___\\ ");
        System.out.println(" \\        /  / __ \\_/ /_/  >");
        System.out.println("  \\__/\\  /  (____  /\\___  / ");
        System.out.println("       \\/        \\/_____/  ");
        printSeparator();
        System.out.println("Hello! I'm Wag");
        System.out.println("What can I do for you?");
        System.out.println("Input 'help' if you wants to know what I can do!!");
        printSeparator();
    }

    /**
     * Displays a separator line for better UI formatting.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a goodbye message when the program exits.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task was deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The total number of tasks remaining in the list.
     */
    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks left in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays a message when a task's status is updated.
     *
     * @param message The status message.
     * @param task    The task that was updated.
     */
    public void printTaskAction(String message, Task task) {
        System.out.println(message);
        System.out.println("  " + task);
    }

    /**
     * Reads user input from the console.
     *
     * @return The user's input as a {@code String}.
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to release system resources.
     */
    public void close() {
        scanner.close();
    }

    public void printMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    public void printHelpMessage() {
        System.out.println("Here are the available commands:");
        System.out.println("- todo <description>: Adds a new todo task");
        System.out.println("- deadline <description> /by <date>: Adds a task with a deadline");
        System.out.println("- event <description> /from <data> /to <date>: Adds an event task");
        System.out.println("- list: Displays all tasks");
        System.out.println("- mark <task number>: Marks a task as done");
        System.out.println("- unmark <task number>: Marks a task as not done");
        System.out.println("- delete <task number>: Deletes a task");
        System.out.println("- find <keyword>: Finds tasks containing the keyword");
        System.out.println("- help: Displays this help message");
        System.out.println("- bye: Exits the application");
    }
}
