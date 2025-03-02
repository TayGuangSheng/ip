package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private int taskId;

    /**
     * Constructs a MarkCommand with the specified task ID.
     *
     * @param taskId The index of the task to be marked as done.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the command by marking the specified task as done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If the task ID is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.markTaskAsDone(taskId);
    }
}
