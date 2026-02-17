package cedricbot;

import java.util.ArrayList;
import java.util.Scanner;

public class CedricBot {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        greetUser();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                sayBye();
                break;
            }

            if (input.equals("list")) {
                printLine();
                if (tasks.isEmpty()) {
                    System.out.println("There are currently no tasks in your list yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                }
                printLine();
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = parseIndex(input, "mark ");
                if (isValidIndex(index, tasks.size())) {
                    tasks.get(index).markDone();

                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    printLine();
                } else {
                    invalidIndex();
                }
                continue;
            }
            if (input.startsWith("unmark ")) {
                int index = parseIndex(input, "unmark ");
                if (isValidIndex(index, tasks.size())) {
                    tasks.get(index).unmark();

                    printLine();
                    System.out.println("OK, I've marked this task as not done yet.");
                    System.out.println("  " + tasks.get(index));
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
               addTask(tasks, task);
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
                addTask(tasks, task);
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
                addTask(tasks, task);
                continue;
            }
            //Level-6
            if (input.startsWith("delete")) {
                int index = parseIndex(input, "delete ");

                if (!isValidIndex(index, tasks.size())) {
                    invalidIndex();
                    continue;
                }
                Task removed = tasks.remove(index);

                printLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removed);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                printLine();
                continue;
            }

            printLine();
            System.out.println("OOPS!!! Incorrect command used :(");
            printLine();
        }
    }

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    private static int parseIndex(String input, String prefix) {
        try {
            int oneBased = Integer.parseInt(input.substring(prefix.length()).trim());
            return oneBased - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
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
        System.out.println("Hello! I'm CedricBot");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("CedricBot, Signing Out! ♥");
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }
}

