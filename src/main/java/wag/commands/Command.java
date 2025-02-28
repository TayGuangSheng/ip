package wag.commands;

import wag.tasks.TaskList;
import wag.exceptions.WagException;
import wag.ui.Ui;

public interface Command {
    /**
     * Executes this command.
     *
     * @param taskList the wag.tasks.TaskList to operate on
     * @param ui the wag.ui.Ui (for printing messages, if needed)
     * @throws WagException if something goes wrong
     */
    void execute(TaskList taskList, Ui ui) throws WagException;
}
