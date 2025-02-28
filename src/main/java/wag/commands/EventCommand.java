package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.Event;
import wag.tasks.TaskList;
import wag.ui.Ui;

public class EventCommand implements Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Event(description, from, to));
    }
}
