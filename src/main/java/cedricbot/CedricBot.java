package cedricbot;

import java.util.ArrayList;

public class CedricBot {
    private static final String DATA_FILE = "data/cedricbot.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public CedricBot() {
        ui = new Ui();
        storage = new Storage(DATA_FILE);

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (Exception e) {
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                ui.showBye();
                break;
            }

            if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    ui.showNoTasks();
                } else {
                    ui.showListHeader();
                    for (int i = 0; i < tasks.size(); i++) {
                        ui.showListItem(i + 1, tasks.get(i));
                    }
                    ui.closeList();
                }
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Parser.parseIndex(input, "mark ");
                if (Parser.isValidIndex(index, tasks.size())) {
                    tasks.get(index).markDone();
                    save();
                    ui.showMark(tasks.get(index));
                } else {
                    ui.showInvalidIndex();
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Parser.parseIndex(input, "unmark ");
                if (Parser.isValidIndex(index, tasks.size())) {
                    tasks.get(index).unmark();
                    save();
                    ui.showUnmark(tasks.get(index));
                } else {
                    ui.showInvalidIndex();
                }
                continue;
            }

            if (input.startsWith("todo ") || input.equals("todo")) {
                String desc = input.length() > "todo".length()
                        ? input.substring("todo".length()).trim()
                        : "";

                if (desc.isEmpty()) {
                    ui.showTodoEmptyError();
                    continue;
                }

                Task task = new Todo(desc);
                tasks.add(task);
                save();
                ui.showAddTask(task, tasks.size());
                continue;
            }

            if (input.startsWith("deadline ")) {
                int byPos = input.indexOf(" /by ");
                if (byPos == -1) {
                    ui.showInvalidFormat("deadline <description> /by <by>");
                    continue;
                }
                String desc = input.substring("deadline ".length(), byPos).trim();
                String by = input.substring(byPos + " /by ".length()).trim();
                Task task = new Deadline(desc, by);

                tasks.add(task);
                save();
                ui.showAddTask(task, tasks.size());
                continue;
            }

            if (input.startsWith("event ")) {

                int fromPos = input.indexOf(" /from ");
                int toPos = input.indexOf(" /to ");

                if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
                    ui.showInvalidFormat("event <description> /from <from> /to <to>");
                    continue;
                }

                String desc = input.substring("event ".length(), fromPos).trim();
                String from = input.substring(fromPos + " /from ".length(), toPos).trim();
                String to = input.substring(toPos + " /to ".length()).trim();

                if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    ui.showInvalidFormat("event <description> /from <from> /to <to>");
                    continue;
                }

                Task task = new Event(desc, from, to);
                tasks.add(task);
                save();
                ui.showAddTask(task, tasks.size());
                continue;
            }

            if (input.startsWith("delete ")) {
                int index = Parser.parseIndex(input, "delete ");
                if (!Parser.isValidIndex(index, tasks.size())) {
                    ui.showInvalidIndex();
                    continue;
                }

                Task removed = tasks.remove(index);
                save();
                ui.showDeleteTask(removed, tasks.size());
                continue;
            }

            if (input.startsWith("find")) {
                String keyword = input.substring("find".length()).trim();

                if (keyword.isEmpty()) {
                    ui.showError("OOPS!!! The keyword to find cannot be empty.");
                    continue;
                }

                ArrayList<Task> matches = tasks.find(keyword);
                ui.showFindResults(matches);
                continue;
            }

            ui.showUnknownCommand();
        }
    }

    private void save() {
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static void main(String[] args) {
        new CedricBot().run();
    }
}

