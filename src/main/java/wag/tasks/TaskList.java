package wag.tasks;

import wag.exceptions.WagException;
import wag.storage.Storage;
import wag.tasks.Task;
import wag.ui.Ui;

public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList(Task[] tasks, int taskCount) {
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    public void addTask(Task task) throws WagException {
        if (taskCount >= tasks.length) {
            throw new WagException("wag.tasks.Task list is full!");
        }
        tasks[taskCount++] = task;
        Storage.save(tasks, taskCount);
        Ui.printTaskAdded(task, taskCount);
    }

    public void deleteTask(int taskId) throws WagException {
        if (taskId < 0 || taskId >= taskCount) {
            throw new WagException("Invalid task id.");
        }
        Task deletedTask = tasks[taskId];
        System.arraycopy(tasks, taskId + 1, tasks, taskId, taskCount - taskId - 1);
        tasks[--taskCount] = null;
        Storage.save(tasks, taskCount);
        Ui.printTaskDeleted(deletedTask, taskCount);
    }

    public void markTaskAsDone(int taskId) throws WagException {
        if (taskId < 0 || taskId >= taskCount) {
            throw new WagException("Invalid task id.");
        }
        tasks[taskId].markAsDone();
        Storage.save(tasks, taskCount);
        Ui.printTaskAction("Nice! I've marked this task as done:", tasks[taskId]);
    }

    public void unmarkTaskAsDone(int taskId) throws WagException {
        if (taskId < 0 || taskId >= taskCount) {
            throw new WagException("Invalid task id.");
        }
        tasks[taskId].unmarkAsDone();
        Storage.save(tasks, taskCount);
        Ui.printTaskAction("OK, I've marked this task as not done yet:", tasks[taskId]);
    }

    public void printTaskList() {
        Ui.printTaskList(tasks, taskCount);
    }

    public int getTaskCount() {
        return taskCount;
    }
}
