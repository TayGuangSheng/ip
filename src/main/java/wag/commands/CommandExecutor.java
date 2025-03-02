package wag.commands;

import wag.exceptions.WagErrorHandler;
import wag.exceptions.WagException;
import wag.parser.Parser;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.Scanner;

/**
 * Handles the execution of user commands in a loop until the user exits.
 */
public class CommandExecutor {
    private TaskList taskList;
    private Ui ui;
    private Scanner scanner;

    /**
     * Constructs a CommandExecutor instance.
     *
     * @param taskList The TaskList containing the user's tasks.
     * @param ui       The Ui instance for interacting with the user.
     */
    public CommandExecutor(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the command execution loop.
     * Continuously reads user input, parses commands, and executes them
     * until the user enters "bye" to exit the program.
     */
    public void start() {
        while (true) {
            try {
                String commandStr = scanner.nextLine();
                ui.printSeparator(); // ✅ Use instance method

                // Handle "bye" command to exit the loop
                if (commandStr.equalsIgnoreCase("bye")) {
                    ui.printGoodbyeMessage();
                    break;
                }
                try {
                    // Use wag.parser.Parser to obtain a wag.commands.Command and execute it.
                    Command command = Parser.parse(commandStr, taskList);
                    command.execute(taskList, ui); // ✅ Pass Ui instance to Command
                    ui.printSeparator(); // ✅ Use instance method
                } catch (IllegalArgumentException e) {
                    // Catch invalid date format errors
                    System.out.println(e.getMessage());
                    ui.printSeparator();
                }

            } catch (WagException e) {
                WagErrorHandler.handleError(e);
            } catch (Exception e) {
                WagErrorHandler.handleError(new WagException("An unexpected error occurred."));
            }
        }
    }
}

