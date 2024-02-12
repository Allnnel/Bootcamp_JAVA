package ex00;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {

  private static int count = 0;

  public static void main(String[] args) throws InterruptedException {
    if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
      getCountFromArgs(args);
      Thread threadOne = new Thread(new MyThread("Egg", count));
      Thread threadTwo = new Thread(new MyThread("Hen", count));

      threadOne.join();
      threadTwo.join();

      for (int i = 0; i != count; i++) {
        Thread.sleep(0);
        out.println("human");
      }
    }
  }

  private static void getCountFromArgs(String[] args) {
    Scanner parsArgs = new Scanner(args[0]).useDelimiter("=");
    if (parsArgs.next().equals("--count") && parsArgs.hasNextInt()) {
      count = parsArgs.nextInt();
    }
  }
}
