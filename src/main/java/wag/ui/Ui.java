package wag.ui;

import wag.tasks.Task;

public class Ui {
    private static final String LOGO = "\n" +
            " __      __                 \n" +
            "/  \\    /  \\_____     ____  \n" +
            "\\   \\/\\/   /\\__  \\   / ___\\ \n" +
            " \\        /  / __ \\_/ /_/  >\n" +
            "  \\__/\\  /  (____  /\\___  / \n" +
            "       \\/        \\/_____/  \n";

    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        printSeparator();
        System.out.println("Hello! I'm Wag");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("_______________________________________");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public static void printTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printTaskList(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void printTaskAction(String message, Task task) {
        System.out.println(message);
        System.out.println("  " + task);
        printSeparator();
    }
}