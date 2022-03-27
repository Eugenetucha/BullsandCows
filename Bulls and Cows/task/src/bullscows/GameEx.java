package bullscows;

class GameEx {

    public static void getEx(int length, int length_sym) throws Exception {
        if (length_sym < length) {
            String message = "Error: it's not possible to generate a code with a length of " + length + " " + "with" + " " + length_sym + " unique symbols.";
            throw new GameException(message);
        }
        if (length > 36 || length_sym > 36) {
            String message = "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).";
            throw new GameException(message);
        }
    }
}

class GameException extends Exception {
    public static void getEx(int length, int length_sym) {
    }

    public GameException(String message) {
        super(message);
    }
}