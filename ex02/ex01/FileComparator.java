package ex01;

import static java.lang.System.out;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class FileComparator {
  private File fileOne;
  private File fileTwo;

  public FileComparator(String OnePath, String twoPath) {
    fileOne = new File(OnePath);
    fileTwo = new File(twoPath);
  }

  public double compareFiles() {
    Map<String, Integer> dictionary = createDictionary();
    double percentage = 0.0;
    try {
      int[] vectorOne = createVector(dictionary, fileOne);
      int[] vectorTwo = createVector(dictionary, fileTwo);
      percentage = calculateSimilarity(vectorOne, vectorTwo);
    } catch (IOException e) {
      out.println(e);
    }
    return percentage;
  }

  private double calculateSimilarity(int[] vectorOne, int[] vectorTwo) {
    double percentage = 0.0;
    double numerator = 0.0;
    double magnitudeOne = 0.0;
    double magnitudeTwo = 0.0;
    for (int i = 0; i != vectorOne.length; i++) {
      numerator += vectorOne[i] * vectorTwo[i];
      magnitudeOne += Math.pow(vectorOne[i], 2);
      magnitudeTwo += Math.pow(vectorTwo[i], 2);
    }

    magnitudeOne = Math.sqrt(magnitudeOne);
    magnitudeTwo = Math.sqrt(magnitudeTwo);

    if (magnitudeOne == 0.0 || magnitudeTwo == 0.0) return 0.0;

    percentage = numerator / (magnitudeOne * magnitudeTwo);

    return percentage;
  }

  private int[] createVector(Map<String, Integer> dictionary, File file) throws IOException {
    int[] vector = new int[dictionary.size()];
    Arrays.fill(vector, 0);
    BufferedReader readerFile = new BufferedReader(new FileReader(file));

    String line = null;
    while ((line = readerFile.readLine()) != null) {
      String[] words = line.split("\\s+");
      for (String word : words) {
        word = word.toLowerCase();
        if (dictionary.containsKey(word)) {
          int index = dictionary.get(word);
          if (index >= 0 && index < vector.length) vector[index]++;
        }
      }
    }

    return vector;
  }

  private Map<String, Integer> createDictionary() {
    Map<String, Integer> dictionary = new TreeMap<>();
    try {
      parserFile(dictionary, fileOne);
      parserFile(dictionary, fileTwo);
      writeDictionaryToFile(dictionary);
    } catch (IOException e) {
      System.out.println(e);
    }
    return dictionary;
  }

  private void parserFile(Map<String, Integer> dictionary, File file) throws IOException {
    String line = null;
    BufferedReader readerFile = new BufferedReader(new FileReader(file));
    while ((line = readerFile.readLine()) != null) {
      String[] words = line.split("\\s+");
      for (String word : words) {
        word = word.toLowerCase();
        dictionary.put(word, dictionary.getOrDefault(word, 0) + 1);
      }
    }
  }

  private void writeDictionaryToFile(Map<String, Integer> dictionary) throws IOException {
    FileWriter fileWriter =
        new FileWriter("/Users/rhogoron/Java_Bootcamp.Day02-1/src/ex01/dictionary.txt");
    for (String word : dictionary.keySet()) {
      fileWriter.write(word + "\n");
    }
    fileWriter.close();
  }
}
