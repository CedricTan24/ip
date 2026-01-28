import java.util.Scanner;

public class CedricBot {
    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[MAX_TASKS];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(LINE);
                continue;
            }

            if (taskCount < MAX_TASKS) {
                tasks[taskCount] = input;
                taskCount++;

                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Your task list is full.");
                System.out.println(LINE);
            }

            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
        }
    }
}
