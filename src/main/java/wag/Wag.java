package wag;

import wag.commands.CommandExecutor;
import wag.storage.Storage;
import wag.tasks.Task;
import wag.tasks.TaskList;
import wag.ui.Ui;

public class Wag {
    public static void main(String[] args) {
        Ui.printWelcomeMessage();

        Task[] tasks = Storage.loadFile();
        int taskCount = Task.getTaskCount(tasks);

        TaskList taskList = new TaskList(tasks, taskCount);
        CommandExecutor commandExecutor = new CommandExecutor(taskList);
        commandExecutor.start();
    }
}
