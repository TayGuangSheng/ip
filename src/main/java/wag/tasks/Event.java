package wag.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return (from != null) ? from.format(OUTPUT_FORMAT) : "Invalid Date";
    }

    public String getTo() {
        return (to != null) ? to.format(OUTPUT_FORMAT) : "Invalid Date";
    }

    public String getStorageFrom() {
        return (from != null) ? from.format(STORAGE_FORMAT) : "";
    }

    public String getStorageTo() {
        return (to != null) ? to.format(STORAGE_FORMAT) : "";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
