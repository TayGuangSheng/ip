package wag.tasks;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task has a description and a due date.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     *
     * @param description The task description.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the deadline of this task.
     *
     * @return The deadline as a {@code String}.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return A string containing the task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
