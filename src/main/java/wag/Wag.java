package wag;

import wag.commands.CommandExecutor;
import wag.storage.Storage;
import wag.tasks.Task;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.ArrayList;

public class Wag {
    public static void main(String[] args) {
        Ui ui = new Ui(); // ✅ Create a Ui instance
        ui.printWelcomeMessage(); // ✅ Call instance method

        ArrayList<Task> tasks = Storage.loadFile();
        TaskList taskList = new TaskList(tasks, ui); // ✅ Pass Ui instance to TaskList
        CommandExecutor commandExecutor = new CommandExecutor(taskList, ui); // ✅ Pass Ui instance to CommandExecutor
        commandExecutor.start();
    }
}
