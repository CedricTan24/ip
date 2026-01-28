import java.util.Scanner;

public class CedricBot {
    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[MAX_TASKS];
        boolean[] isDone = new boolean[MAX_TASKS];
        int taskCount = 0;

        System.out.println(LINE);
        System.out.println("Hello! I'm CedricBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("CedricBot, Signing Out! ♥");
                System.out.println(LINE);
                break;
            }

            if (input.equals("list")) {
                System.out.println(LINE);
                if (taskCount == 0) {
                    System.out.println("There are currently no tasks in your list yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        String status = isDone[i] ? "X" : " ";
                        System.out.println((i + 1) + ".[" + status + "] " + tasks[i]);
                    }
                }
                System.out.println(LINE);
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = parseIndex(input, "mark ");
                if (isValidIndex(index, taskCount)) {
                    isDone[index] = true;

                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + tasks[index]);
                    System.out.println(LINE);
                } else {
                    invalidIndex();
                }
                continue;
            }
            if (input.startsWith("unmark ")) {
                int index = parseIndex(input, "unmark ");
                if (isValidIndex(index, taskCount)) {
                    isDone[index] = false;

                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet.");
                    System.out.println("  [ ] " + tasks[index]);
                    System.out.println(LINE);
                } else {
                    invalidIndex();
                }
                continue;
            }

            if (taskCount < MAX_TASKS) {
                tasks[taskCount] = input;
                isDone[taskCount] = false;
                taskCount++;

                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Your task list is full.");
                System.out.println(LINE);
            }
        }
    }

    private static int parseIndex(String input, String prefix) {
        try {
            int oneBase = Integer.parseInt(input.substring(prefix.length()).trim());
            return oneBase - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isValidIndex(int index, int taskCount) {
        return index >= 0 && index < taskCount;
    }

    private static void invalidIndex() {
        System.out.println(LINE);
        System.out.println("Please give a valid task number.");
        System.out.println(LINE);
    }

}

