package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.tasks.Todo;
import wag.ui.Ui;

/**
 * Represents a command to display a help message with all available commands and their usage.
 */
public class HelpCommand implements Command {
    /**
     * Executes the help command by displaying all available commands and their usage.
     *
     * @param ui The Ui instance for user interaction.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printHelpMessage();
    }
}