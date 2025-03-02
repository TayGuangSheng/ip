package wag.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 * An {@code Event} task has a description, a start time, and an end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    public Event(String description, String from, String to) throws IllegalArgumentException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: dd/MM/yyyy HHmm");
        }
    }

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
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
        return (from != null) ? from.format(OUTPUT_FORMAT) : "Invalid Date";
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a {@code String}.
     */
    public String getTo() {
        return (to != null) ? to.format(OUTPUT_FORMAT) : "Invalid Date";
    }

    public String getStorageFrom() {
        return (from != null) ? from.format(STORAGE_FORMAT) : "";
    }

    public String getStorageTo() {
        return (to != null) ? to.format(STORAGE_FORMAT) : "";
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A string containing the task type, status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
