package ex02;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {

  private static int count = 3;
  private static int size = 13;

  public static void main(String[] args) {
    if (args.length > 1
        && args[0] != null
        && !args[0].isEmpty()
        && args[1] != null
        && !args[1].isEmpty()) {
      getCountFromArgs(args);

      out.println("Sum: " + size);
      int[] randomArray = generateRandomArray();

      int elementsPerPart = size / count;
      int remainingElements = size % count;

      int currentIndex = 0;
      for (int i = 1; i <= count; i++) {
        int currentPartSize = elementsPerPart + (i <= remainingElements ? 1 : 0);
        int endIndex = currentIndex + currentPartSize - 1;
        new Thread(new MyThread(randomArray, currentIndex, endIndex, i));
        currentIndex = endIndex + 1;
      }

      out.println("Sum by threads: " + Arrays.stream(randomArray).sum());
    }
  }

  private static int[] generateRandomArray() {
    Random random = new Random();
    int[] array = new int[size];
    for (int i = 0; i != size; i++) {
      array[i] = random.nextInt();
    }
    return array;
  }

  private static void getCountFromArgs(String[] args) {
    Scanner parsArgs = new Scanner(args[0]).useDelimiter("=");
    if (parsArgs.next().equals("--arraySize") && parsArgs.hasNextInt()) {
      size = parsArgs.nextInt();
    }
    parsArgs = new Scanner(args[1]).useDelimiter("=");
    if (parsArgs.next().equals("--threadsCount") && parsArgs.hasNextInt()) {
      count = parsArgs.nextInt();
    }
  }
}
