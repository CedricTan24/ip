package cedricbot;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an Event task with a description, start time and end time.
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the storage format of this Event task.
     * @return The formatted storage string for file saving.
     */
    @Override
    public String toDataString() {
        return "E | " + getStatusForFile() + " | " + description + " | " + from + " | " + to;
    }
}
