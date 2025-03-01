package wag.tasks;

import wag.exceptions.WagException;
import wag.storage.Storage;
import wag.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui; // ✅ Store Ui instance

    public TaskList(Ui ui) { // ✅ Pass Ui instance
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList(ArrayList<Task> tasks, Ui ui) { // ✅ Pass Ui instance
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.printTaskAdded(task, tasks.size());
        Storage.save(tasks); // ✅ Save after adding a task
    }

    public void deleteTask(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        Task removedTask = tasks.remove(taskId);
        ui.printTaskDeleted(removedTask, tasks.size());
        Storage.save(tasks); // ✅ Save after deleting a task
    }

    public void markTaskAsDone(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        tasks.get(taskId).markAsDone();
        ui.printTaskAction("Nice! I've marked this task as done:", tasks.get(taskId));
        Storage.save(tasks); // ✅ Save after marking as done
    }

    public void unmarkTaskAsDone(int taskId) throws WagException {
        if (!isValidTaskIndex(taskId)) {
            throw new WagException("Invalid task ID.");
        }
        tasks.get(taskId).unmarkAsDone();
        ui.printTaskAction("OK, I've marked this task as not done yet:", tasks.get(taskId));
        Storage.save(tasks); // ✅ Save after unmarking
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void printTaskList() {
        ui.printTaskList(tasks); // ✅ Use instance method
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

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
