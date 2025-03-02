package wag.commands;

import wag.exceptions.WagException;
import wag.tasks.Task;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws WagException {
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}
