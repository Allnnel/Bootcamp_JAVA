package ex03;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

  static String pathFile = "/Users/rhogoron/Java_Bootcamp.Day03-1/src/ex03/files_urls.txt";
  private static int count = 0;

  public static void main(String[] args) {
    if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
      getCountFromArgs(args);
      try {
        parsFile();
      } catch (IOException | InterruptedException e) {
        out.println(e);
      }
    }
  }

  private static void parsFile() throws IOException, InterruptedException {
    String URL = null;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
    ExecutorService executorService = Executors.newFixedThreadPool(count);
    for (int i = 0; (URL = bufferedReader.readLine()) != null; i++) {
      executorService.execute(new MyThread(URL, i));
    }
    executorService.shutdown();
  }

  private static void getCountFromArgs(String[] args) {
    Scanner parsArgs = new Scanner(args[0]).useDelimiter("=");
    if (parsArgs.next().equals("--threadsCount") && parsArgs.hasNextInt()) {
      count = parsArgs.nextInt();
      count = count - 1;
    }
  }
}
