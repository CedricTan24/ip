package cedricbot;

public class Parser {
    public static int parseIndex(String input, String prefix) {
        try {
            int oneBased = Integer.parseInt(input.substring(prefix.length()).trim());
            return oneBased - 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
