package cedricbot;

/**
 * Represents a task that has a specific deadline.
 */
public class Deadline extends Task{
    private String by;

    /**
     * Creates a Deadline task with a description and due date.
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the storage format of this Deadline task.
     * @return The formatted storage string for file saving.
     */
    @Override
    public String toDataString() {
        return "D | " + getStatusForFile() + " | " + description + " | " + by;
    }
}
