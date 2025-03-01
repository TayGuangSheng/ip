package wag.ui;

import wag.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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
        printSeparator();
    }


    public void printSeparator() {
        System.out.println("_______________________________________");
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void printTaskAction(String message, Task task) {
        System.out.println(message);
        System.out.println("  " + task);
        printSeparator();
    }

    public String readUserInput() {
        return scanner.nextLine();
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
}
