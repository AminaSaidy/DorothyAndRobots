package uzb.aminasaidakhmedova.dorothyandrobots.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _Main {
    public static void main(String[] args) {
        String resourcesRode = "src/main/resources/";
        String inputFile = resourcesRode + "text.txt";
        String firstErrorWord = null;
        String secondErrorWord = null;
        Set<String> evenVowelsWords = new HashSet<>();
        Set<String> oddVowelsWords = new HashSet<>();
        Map<Character, Integer> numberingPunctuation = new HashMap<>();

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
//                            firstErrorFile = resourcesRode + deleteErrFromWord(word) + ".txt";
                            firstErrorWord = word;
                        } else if (errorWordCount == 2) {
//                            secondErrorFile = resourcesRode + deleteErrFromWord(word) + ".txt";
                            secondErrorWord = word;
                        }
                    } else {
                        countPunctuation(word, numberingPunctuation);
                        if (countVowels(word) % 2 == 0){
                            evenVowelsWords.add(word);
                        } else {
                            oddVowelsWords.add(word);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        createAndWriteToFile (resourcesRode + firstErrorWord + ".txt", evenVowelsWords.toString());
        createAndWriteToFile(resourcesRode + secondErrorWord + ".txt", oddVowelsWords.toString());
        createAndWriteToFile("punctuation");
    }

    public static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z].*]");
    }

    public static void createAndWriteToFile(String fileName, String content) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String deleteErrFromWord(String word) {
        return word.replaceAll(".*[^a-zA-Z].*", "");
    }

    public static int countVowels(String word) {
        int count = 0;
        for (char character : word.toCharArray()) {
            if("AEIOUaeiou".indexOf(character) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void countPunctuation (String word, Map<Character, Integer> signsNumbered) {
        for (char symbol : word.toCharArray()) {
            if(",.?!:;-".indexOf(symbol) != -1) {
                signsNumbered.put(symbol, signsNumbered.getOrDefault(symbol, 0) + 1);
            }
        }
    }


}

