# CedricBot User Guide

CedricBot is a Command Line Interface (CLI) task management application designed to help users efficiently manage todos, deadlines, and events through simple text commands.

It automatically saves tasks and loads them when restarted.

---

## Features

## Adding Todos

Adds a simple task without any date or time constraints.

**Format:**
```
todo <description>
```

**Example:**
```
todo read book
```

**Expected outcome:**
```
[T][ ] read book
```

---

## Adding Deadlines

Adds a task with a due date.

**Format:**
```
deadline <description> /by <due date>
```

**Example:**
```
deadline return book /by Sunday
```

**Expected outcome:**
```
[D][ ] return book (by: Sunday)
```

---

## Adding Events

Adds a task with a start and end time.

**Format:**
```
event <description> /from <start> /to <end>
```

**Example:**
```
event project meeting /from Mon 2pm /to 4pm
```

**Expected outcome:**
```
[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

---

## Listing Tasks

Displays all tasks currently stored.

**Format:**
```
list
```

---

## Marking a Task

Marks a task as completed.

**Format:**
```
mark <task number>
```

---

## Unmarking a Task

Marks a completed task as not done.

**Format:**
```
unmark <task number>
```

---

## Deleting a Task

Removes a task from the list.

**Format:**
```
delete <task number>
```

---

## Finding Tasks

Finds tasks containing a keyword in their description.

**Format:**
```
find <keyword>
```

**Example:**
```
find book
```

**Expected outcome:**
Displays all tasks that contain the word "book".

---

## Exiting the Program

Exits CedricBot.

**Format:**
```
bye
```

---

## Data Storage

CedricBot automatically saves tasks into a local data file and loads them upon startup.

---

## Notes

- Task numbers correspond to the list shown using the `list` command.
- Commands are case-sensitive.
- Invalid input formats will result in error messages.

