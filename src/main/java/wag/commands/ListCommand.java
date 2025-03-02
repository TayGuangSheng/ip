package wag.commands;

import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by displaying the list of tasks.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui instance for user interaction.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.printTaskList();
    }
}
