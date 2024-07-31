package uzb.aminasaidakhmedova.dorothyandrobots.model;

public class _Main {
    public static void main (String[] args) {

    }

    private static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z].*]");
    }
}

