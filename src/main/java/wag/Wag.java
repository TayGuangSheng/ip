package wag;

import wag.commands.CommandExecutor;
import wag.storage.Storage;
import wag.tasks.Task;
import wag.tasks.TaskList;
import wag.ui.Ui;

import java.util.ArrayList;

/**
 * The main class for the Wag task management application.
 * It initializes the user interface, loads tasks from storage,
 * and starts the command execution loop.
 */
public class Wag {

    /**
     * The entry point of the application. Initializes UI, loads tasks,
     * and starts the command execution loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the user interface
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        // Load tasks from storage
        ArrayList<Task> tasks = Storage.loadFile();
        TaskList taskList = new TaskList(tasks, ui);

        // Start the command execution loop
        CommandExecutor commandExecutor = new CommandExecutor(taskList, ui);
        commandExecutor.start();
    }
}
