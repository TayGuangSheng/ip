package wag.tasks;

/**
 * Represents a generic task that can be marked as done or not done.
 * This is an abstract class meant to be extended by specific task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done (sets it back to not done).
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is done, otherwise {@code false}.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the status icon representing whether the task is done.
     * "[X]" represents a completed task, and "[ ]" represents an incomplete task.
     *
     * @return The status icon as a {@code String}.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a formatted string representation of the task,
     * including its status and description.
     *
     * @return A string containing the task's status and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
