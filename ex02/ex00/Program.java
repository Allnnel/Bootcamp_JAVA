package ex00;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    FileWriterExample fileWriterExample = new FileWriterExample();
    String filePath = null;

    while (true) {
      filePath = scanner.nextLine();
      if (filePath.equals("42")) break;
      out.println("PROCESSED");
      SignatureAnalyzer signatureAnalyzer = new SignatureAnalyzer(filePath);
      String format = signatureAnalyzer.detectFormat();
      fileWriterExample.writeStringToFile(format);
    }
  }
}
