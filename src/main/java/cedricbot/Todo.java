package cedricbot;

/**
 * Represents a simple task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task with the given description.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the storage format of this Todo task.
     * @return The formatted storage string for file saving.
     */
    @Override
    public String toDataString() {
        return "T | " + getStatusForFile() + " | " + description;
    }
}
