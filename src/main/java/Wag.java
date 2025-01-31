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

        String command = "";

        // Loop to handle user input and echo the command until "bye" is typed
        while (!command.equalsIgnoreCase("bye")) {
            command = scanner.nextLine();  // Get user input
            System.out.println("_______________________________________");
            System.out.println(command);  // Echo the command
            System.out.println("_______________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }
}
