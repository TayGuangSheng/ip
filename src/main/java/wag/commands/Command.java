package wag.commands;

import wag.tasks.TaskList;
import wag.exceptions.WagException;
import wag.ui.Ui;

/**
 * Represents a command that can be executed on a task list.
 * Implementing classes should define specific command behaviors.
 */
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
