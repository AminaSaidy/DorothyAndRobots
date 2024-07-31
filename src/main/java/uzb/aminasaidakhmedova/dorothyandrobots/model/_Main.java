package uzb.aminasaidakhmedova.dorothyandrobots.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _Main {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("text.txt");
             BufferedReader br = new BufferedReader(fr)) {

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z].*]");
    }
}

