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

    public static String parseFind(String input) throws Exception {
        String keyword = input.substring("find".length()).trim();

        if (keyword.isEmpty()) {
            throw new Exception("OOPS!!! The keyword to find cannot be empty.");
        }

        return keyword;
    }
}
