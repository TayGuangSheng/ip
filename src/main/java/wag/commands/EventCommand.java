package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.Event;
import wag.tasks.TaskList;
import wag.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand implements Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with the specified description, start, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding an event task to the task list.
     *
     * @param taskList The TaskList to which the event will be added.
     * @param ui       The Ui instance for user interaction.
     * @throws WagException If an error occurs while adding the event.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Event(description, from, to));
    }
}

