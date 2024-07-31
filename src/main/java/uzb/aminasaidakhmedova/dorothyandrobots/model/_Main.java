package uzb.aminasaidakhmedova.dorothyandrobots.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class _Main {
    public static void main(String[] args) {
        String resourcesRode = "src/main/resources/";
        String inputFile = resourcesRode + "text.txt";
        String firstErrorFile = null;
        String secondErrorFile = null;

        try (FileReader fr = new FileReader(inputFile);
             BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            int errorWordCount = 0;

            while (line != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (containsNonsenseChars(word)) {
                        errorWordCount++;
                        if (errorWordCount == 1) {
                            firstErrorFile = resourcesRode + deleteErrFromWord(word) + ".txt";
                            createFile(firstErrorFile);
                        } else if (errorWordCount == 2) {
                            secondErrorFile = resourcesRode + deleteErrFromWord(word) + ".txt";
                            createFile(secondErrorFile);
                            break;
                        }
                    }
                    if (errorWordCount == 2) {
                        break;
                    }
                }
            }
            System.out.println("Files 1 and 2 were created.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        createFile("punctuation");
    }

    private static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z].*]");
    }

    private static void createFile(String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String deleteErrFromWord(String word) {
        return word.replaceAll(".*.[^a-zA-Z]*", "");
    }
}

