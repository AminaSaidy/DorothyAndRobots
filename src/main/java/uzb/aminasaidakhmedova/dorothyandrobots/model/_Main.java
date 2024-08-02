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
            String line;
            int errorWordCount = 0;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (containsNonsenseChars(word)) {
                        errorWordCount++;
                        if (errorWordCount == 1) {
                            firstErrorWord = deleteErrFromWord(word);
                        } else if (errorWordCount == 2) {
                            secondErrorWord = deleteErrFromWord(word);
                        }
                    } else {
                        countPunctuation(word, numberingPunctuation);
                        if(!word.equals(firstErrorWord) && !word.equals(secondErrorWord)) {
                            String cleanWord = deleteErrFromWord(word);
                            if (countVowels(cleanWord) % 2 == 0 && !evenVowelsWords.contains(cleanWord)) {
                                evenVowelsWords.add(cleanWord);
                            }
                            if(countVowels(cleanWord) % 2 == 1 && !oddVowelsWords.contains(cleanWord)) {
                                oddVowelsWords.add(cleanWord);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        createAndWriteToFile(resourcesRode + firstErrorWord + ".txt", evenVowelsWords);
        createAndWriteToFile(resourcesRode + secondErrorWord + ".txt", oddVowelsWords);
        createWriteToPunctFile(resourcesRode + "punctuation.txt", numberingPunctuation);
    }

    public static boolean containsNonsenseChars(String word) {
        return word.matches(".*[^a-zA-Z\\p{Punct}].*");
    }

    public static void createAndWriteToFile(String fileName, Set<String> content) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(String.join(" ", content));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String deleteErrFromWord(String word) {
        return word.replaceAll("[^a-zA-Z]", "");
    }

    public static int countVowels(String word) {
        int count = 0;
        for (char character : word.toCharArray()) {
            if ("AEIOUaeiou".indexOf(character) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void countPunctuation(String word, Map<Character, Integer> signsNumbered) {
        for (char symbol : word.toCharArray()) {
            if (",.?!:;-".indexOf(symbol) != -1) {
                signsNumbered.put(symbol, signsNumbered.getOrDefault(symbol, 0) + 1);
            }
        }
    }

    public static void createWriteToPunctFile(String fileName, Map<Character, Integer> countedPunctuation) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Map.Entry<Character, Integer> element : countedPunctuation.entrySet()) {
                fw.write(element.getKey() + " " + element.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

