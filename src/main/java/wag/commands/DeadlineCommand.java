package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.Deadline;
import wag.tasks.TaskList;
import wag.ui.Ui;

public class DeadlineCommand implements Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Deadline(description, by));
    }
}
