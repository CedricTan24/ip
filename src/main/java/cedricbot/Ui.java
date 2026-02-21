package cedricbot;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm CedricBot");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("CedricBot, Signing Out! ♥");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showInvalidFormat(String expected) {
        showLine();
        System.out.println("OOPS!!! Invalid command format.");
        System.out.println("Expected: " + expected);
        showLine();
    }

    public void showInvalidIndex() {
        showLine();
        System.out.println("OOPS!!! Please give a valid task number.");
        showLine();
    }

    public void showUnknownCommand() {
        showLine();
        System.out.println("OOPS!!! Incorrect command used :(");
        showLine();
    }

    public void showTodoEmptyError() {
        showLine();
        System.out.println("OOPS!!! The description of a todo cannot be empty. :(");
        showLine();
    }

    public void showAddTask(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showDeleteTask(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showMark(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        showLine();
    }

    public void showUnmark(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet.");
        System.out.println("  " + task);
        showLine();
    }

    public void showListHeader() {
        showLine();
        System.out.println("Here are the tasks in your list:");
    }

    public void showNoTasks() {
        showLine();
        System.out.println("There are currently no tasks in your list yet.");
        showLine();
    }

    public void showListItem(int idx, Task task) {
        System.out.println(idx + "." + task);
    }

    public void closeList() {
        showLine();
    }
}
