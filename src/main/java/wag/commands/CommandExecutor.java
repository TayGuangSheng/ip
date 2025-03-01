package wag.commands;

import wag.exceptions.WagErrorHandler;
import wag.exceptions.WagException;
import wag.parser.Parser;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.Scanner;

public class CommandExecutor {
    private TaskList taskList;
    private Ui ui;
    private Scanner scanner;

    public CommandExecutor(TaskList taskList, Ui ui) { // ✅ Pass Ui instance
        this.taskList = taskList;
        this.ui = ui;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            try {
                String commandStr = scanner.nextLine();
                ui.printSeparator(); // ✅ Use instance method

                // "bye" is still handled here.
                if (commandStr.equalsIgnoreCase("bye")) {
                    ui.printGoodbyeMessage(); // ✅ Use instance method
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

