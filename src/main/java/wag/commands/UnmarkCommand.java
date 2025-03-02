package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private int taskId;

    /**
     * Constructs an UnmarkCommand with the specified task ID.
     *
     * @param taskId The ID of the task to be unmarked.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the command by marking the specified task as not done.
     *
     * @param taskList The TaskList containing the task.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If the task ID is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.unmarkTaskAsDone(taskId);
    }
}
