/**
 * Represents a generic task with a description and a completion status.
 */

package cedricbot;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the status icon used for display.
     * @return "X" if completed, otherwise a blank space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the status value used for file storage.
     * @return "1" if completed, otherwise "0"
     */
    public String getStatusForFile() {
        return isDone ? "1" : "0";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of this task formatted for saving into the data file.
     * @return The formatted storage string.
     */
    public abstract String toDataString();

    public String getDescription() {
        return description;
    }
}
