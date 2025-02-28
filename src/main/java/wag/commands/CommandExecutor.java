package wag.commands;

import wag.exceptions.WagErrorHandler;
import wag.exceptions.WagException;
import wag.parser.Parser;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.Scanner;

public class CommandExecutor {
    private TaskList taskList;
    private Scanner scanner;

    public CommandExecutor(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            try {
                String commandStr = scanner.nextLine();
                Ui.printSeparator();

                // "bye" is still handled here.
                if (commandStr.equalsIgnoreCase("bye")) {
                    Ui.printGoodbyeMessage();
                    break;
                }

                // Use wag.parser.Parser to obtain a wag.commands.Command and execute it.
                Command command = Parser.parse(commandStr, taskList);
                command.execute(taskList, null); // wag.ui.Ui methods are static so no instance is needed
                Ui.printSeparator();
            } catch (WagException e) {
                WagErrorHandler.handleError(e);
            } catch (Exception e) {
                WagErrorHandler.handleError(new WagException("An unexpected error occurred."));
            }
        }
    }
}
