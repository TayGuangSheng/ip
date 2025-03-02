package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private int taskId;

    /**
     * Constructs a DeleteCommand with the specified task ID.
     *
     * @param taskId The index of the task to be deleted (0-based).
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the delete command, removing the specified task from the task list.
     *
     * @param taskList The TaskList from which the task will be deleted.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If the task ID is invalid or an error occurs during deletion.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.deleteTask(taskId);
    }
}
