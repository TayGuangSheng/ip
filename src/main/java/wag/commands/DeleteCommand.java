package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.TaskList;
import wag.ui.Ui;

public class DeleteCommand implements Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        taskList.deleteTask(taskId);
    }
}
