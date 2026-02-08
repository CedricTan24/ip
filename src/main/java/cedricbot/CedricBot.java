package cedricbot;

import java.util.Scanner;

public class CedricBot {
    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        greetUser();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                sayBye();
                break;
            }

            if (input.equals("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println("There are currently no tasks in your list yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                }
                printLine();
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = parseIndex(input, "mark ");
                if (isValidIndex(index, taskCount)) {
                    tasks[index].markDone();

                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    invalidIndex();
                }
                continue;
            }
            if (input.startsWith("unmark ")) {
                int index = parseIndex(input, "unmark ");
                if (isValidIndex(index, taskCount)) {
                    tasks[index].unmark();

                    printLine();
                    System.out.println("OK, I've marked this task as not done yet.");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    invalidIndex();
                }
                continue;
            }
            //Level-4,5
            if (input.startsWith("todo ") || input.equals("todo")) {
               String desc = input.length() > "todo".length() ? input.substring("todo".length()).trim() : "";

               if (desc.isEmpty()) {
                   printLine();
                   System.out.println("OOPS!!! The description of a todo cannot be empty. :(");
                   printLine();
                   continue;
               }

               Task task = new Todo(desc);
               taskCount = addTask(tasks, taskCount, task);
               continue;
            }

            if (input.startsWith("deadline ")) {
                int byPos = input.indexOf(" /by ");
                if (byPos == -1) {
                    invalidFormat("deadline <description> /by <by>");
                    continue;
                }
                String desc = input.substring("deadline ".length(), byPos).trim();
                String by = input.substring(byPos + " /by ".length()).trim();
                Task task = new Deadline(desc, by);
                taskCount = addTask(tasks, taskCount, task);
                continue;
            }

            if (input.startsWith("event ")) {
                int fromPos = input.indexOf("/from");
                int toPos = input.indexOf("/to");

                if (fromPos == -1 || toPos == -1 || toPos < fromPos) {
                    invalidFormat("event <description> /from <from> /to <to>");
                    continue;
                }

                String desc = input.substring("event ".length(), fromPos).trim();
                String from = input.substring(fromPos + "/from".length(), toPos).trim();
                String to = input.substring(toPos + "/to".length()).trim();

                if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    invalidFormat("event <description> /from <from> /to <to>");
                    continue;
                }

                Task task = new Event(desc, from, to);
                taskCount = addTask(tasks, taskCount, task);
                continue;
            }

            printLine();
            System.out.println("OOPS!!! Incorrect command used :(");
            printLine();
        }
    }

    private static int addTask(Task[] tasks, int taskCount, Task task) {
        if (taskCount >= MAX_TASKS) {
            printLine();
            System.out.println("Your task list is full.");
            printLine();
            return taskCount;
        }

        tasks[taskCount] = task;
        taskCount++;

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();

        return taskCount;
    }

    private static int parseIndex(String input, String prefix) {
        try {
            int oneBased = Integer.parseInt(input.substring(prefix.length()).trim());
            return oneBased - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isValidIndex(int index, int taskCount) {
        return index >= 0 && index < taskCount;
    }

    private static void invalidFormat(String expected) {
        printLine();
        System.out.println("OOPS!!! Invalid command format.");
        System.out.println("Expected: " + expected);
        printLine();
    }

    private static void invalidIndex() {
        printLine();
        System.out.println("OOPS!!! Please give a valid task number.");
        printLine();
    }

    private static void greetUser() {
        printLine();
        System.out.println("Hello! I'm cedricbot.CedricBot");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("cedricbot.CedricBot, Signing Out! ♥");
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }
}

