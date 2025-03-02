package wag.tasks;

import wag.exceptions.WagException;
import wag.storage.Storage;
import wag.ui.Ui;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing functionality to add, remove,
 * mark, unmark, and retrieve tasks while ensuring persistence via storage.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui; // ✅ Store Ui instance

    /**
     * Constructs a new empty {@code TaskList} with a UI instance.
     *
     * @param ui The UI instance for displaying task-related messages.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Constructs a {@code TaskList} with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     * @param ui The UI instance for displaying task-related messages.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Adds a task to the list and updates storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.printTaskAdded(task, tasks.size());
        Storage.save(tasks); // ✅ Save after adding a task
    }

    /**
     * Deletes a task from the list by its ID.
     * Ensures the task ID is valid before attempting deletion.
     *
     * @param taskId The index of the task to be removed.
     * @throws WagException If the task ID is invalid.
     */
    public void deleteTask(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        Task removedTask = tasks.remove(taskId);
        ui.printTaskDeleted(removedTask, tasks.size());
        Storage.save(tasks); // ✅ Save after deleting a task
    }

    /**
     * Marks a task as done.
     *
     * @param taskId The index of the task to mark as done.
     * @throws WagException If the task ID is invalid.
     */
    public void markTaskAsDone(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        tasks.get(taskId).markAsDone();
        ui.printTaskAction("Nice! I've marked this task as done:", tasks.get(taskId));
        Storage.save(tasks); // ✅ Save after marking as done
    }

    /**
     * Unmarks a task, setting it back to not done.
     *
     * @param taskId The index of the task to unmark.
     * @throws WagException If the task ID is invalid.
     */
    public void unmarkTaskAsDone(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        tasks.get(taskId).unmarkAsDone();
        ui.printTaskAction("OK, I've marked this task as not done yet:", tasks.get(taskId));
        Storage.save(tasks); // ✅ Save after unmarking
    }

    /**
     * Retrieves the total number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Prints the current list of tasks using the UI instance.
     */
    public void printTaskList() {
        ui.printTaskList(tasks); // ✅ Use instance method
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if a given task ID is valid within the current task list.
     *
     * @param taskId The task index to validate.
     * @return {@code true} if the index is within range, otherwise {@code false}.
     */
    public boolean isValidTaskIndex(int taskId) {
        return taskId >= 0 && taskId < tasks.size();
    }
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
