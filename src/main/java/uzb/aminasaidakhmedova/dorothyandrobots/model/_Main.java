package uzb.aminasaidakhmedova.dorothyandrobots.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _Main {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("text.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            int errorWordCounter = 0;

            while (line != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (containsNonsenseChars(word)) {
                        errorWordCounter++;
                    }
                    if(errorWordCounter == 1) {
                        //code to create and name a file with this error word
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z].*]");
    }
}

