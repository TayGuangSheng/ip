package wag.tasks;

/**
 * Represents an event task with a start and end time.
 * An {@code Event} task has a description, a start time, and an end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a {@code String}.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a {@code String}.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A string containing the task type, status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
