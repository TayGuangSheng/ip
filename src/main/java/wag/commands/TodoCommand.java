package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.tasks.Todo;
import wag.ui.Ui;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class TodoCommand implements Command {
    private String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by adding a new Todo task to the task list.
     *
     * @param taskList The TaskList where the task will be added.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If an error occurs while adding the task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Todo(description));
    }
}
