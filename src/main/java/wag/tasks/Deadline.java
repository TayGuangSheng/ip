package wag.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task has a description and a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     *
     * @param description The task description.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the deadline of this task.
     *
     * @return The deadline as a {@code String}.
     */
    public String getBy() {
        return (by != null) ? by.format(OUTPUT_FORMAT) : "Invalid Date";
    }

    public String getStorageFormat() {
        return (by != null) ? by.format(STORAGE_FORMAT) : "";
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return A string containing the task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
