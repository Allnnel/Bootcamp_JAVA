package ex01;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    String oneFile, twoFile;
    oneFile = scanner.nextLine();
    twoFile = scanner.nextLine();
    FileComparator fileComparator = new FileComparator(oneFile, twoFile);
    out.println("Similarity = " + fileComparator.compareFiles());
  }
}
