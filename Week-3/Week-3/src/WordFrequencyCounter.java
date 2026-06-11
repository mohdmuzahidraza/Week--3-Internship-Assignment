import java.io.*;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {

        String inputFile  = "input.txt";
        String outputFile = "output.txt";

        HashMap<String, Integer> wordMap = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s+"); // split by spaces

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
            reader.close();
            System.out.println("✔ File read successfully!");

        } catch (FileNotFoundException e) {
            System.out.println("✘ Error: input.txt not found!");
            return;
        } catch (IOException e) {
            System.out.println("✘ Error reading file: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("--- Word Frequency Report ---\n\n");

            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                String result = entry.getKey() + " : " + entry.getValue();
                System.out.println(result);
                writer.write(result + "\n");
            }

            writer.close();
            System.out.println("\n✔ Output written to " + outputFile);

        } catch (IOException e) {
            System.out.println("✘ Error writing file: " + e.getMessage());
        }
    }
}