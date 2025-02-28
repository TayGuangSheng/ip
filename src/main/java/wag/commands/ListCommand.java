package wag.commands;

import wag.tasks.TaskList;
import wag.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.printTaskList();
    }
}
