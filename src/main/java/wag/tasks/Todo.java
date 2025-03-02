package wag.tasks;

/**
 * Represents a ToDo task that does not have any specific date or time.
 * Inherits from the {@code Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string with task type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
