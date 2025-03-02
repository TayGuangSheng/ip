package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.Deadline;
import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand implements Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand with a task description and a deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by adding a new deadline task to the task list.
     *
     * @param taskList The TaskList where the task will be added.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If an error occurs while adding the task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Deadline(description, by));
    }
}

