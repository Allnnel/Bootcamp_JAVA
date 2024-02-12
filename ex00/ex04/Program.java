package ex04;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    if (str.length() != 0) {
      char[] inputStr = str.toCharArray();
      short[] characterCount = insertionSort(inputStr);
      printGraph(characterCount);
    }
    scanner.close();
  }

  private static short[] insertionSort(char[] inputStr) {
    short[] characterCountASCII = new short[256];
    for (char currentChar : inputStr) {
      characterCountASCII[(int) currentChar] += 1;
    }
    return characterCountASCII;
  }

  private static void printGraph(short[] characterCount) {
    int[] maxIndex = new int[10];
    short[] elemCounts = new short[10];
    maxIndex = maxSize(characterCount, elemCounts);

    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < maxIndex.length - 1; j++) {
        int print = (int) Math.round((elemCounts[j] * 10) / elemCounts[0]);
        if (i != 0 && print >= 10 - i + 1) out.printf("%-3s", "#");
        else if (print >= 10 - i && elemCounts[j] != 0) out.printf("%-3s", elemCounts[j]);
        else out.printf("%-3s", "");
      }
      out.println();
    }

    for (int z = 0; z < maxIndex.length - 1; z++) out.printf("%-3s", (char) maxIndex[z]);

    out.println();
  }

  private static int[] maxSize(short[] characterCount, short[] count) {
    int[] maxIndex = new int[11];
    for (int i = 0; i < 10; i++) {
      maxIndex[i] = maxCount(characterCount);
      count[i] = characterCount[maxIndex[i]];
      characterCount[maxIndex[i]] = 0;
    }
    return maxIndex;
  }

  private static int maxCount(short[] characterCount) {
    int index = 0;
    for (int i = 1; i < characterCount.length; i++)
      if (characterCount[i] > characterCount[index]) index = i;

    return index;
  }
}
