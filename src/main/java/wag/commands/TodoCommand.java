package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.tasks.Todo;
import wag.ui.Ui;

public class TodoCommand implements Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.addTask(new Todo(description));
    }
}
