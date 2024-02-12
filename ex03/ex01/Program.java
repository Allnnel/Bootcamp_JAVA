package ex01;

import java.util.Scanner;

public class Program {
  enum Type {
    CONSUMER,
    PRODUCER
  }

  private static int count = 10;

  public static void main(String[] args) throws InterruptedException, IllegalMonitorStateException {
    if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
      getCountFromArgs(args);
      Print print = new Print();
      Thread threadOne = new Thread(new MyThread("Egg", count, Type.CONSUMER, print));
      Thread threadTwo = new Thread(new MyThread("Hen", count, Type.PRODUCER, print));
    }
  }

  private static void getCountFromArgs(String[] args) {
    Scanner parsArgs = new Scanner(args[0]).useDelimiter("=");
    if (parsArgs.next().equals("--count") && parsArgs.hasNextInt()) {
      count = parsArgs.nextInt();
    }
  }
}
