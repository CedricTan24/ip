import java.util.Scanner;

public class CedricBot {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
        }
    }
}
