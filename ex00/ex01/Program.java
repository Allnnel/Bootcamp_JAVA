package ex01;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  private static int iteration = 0;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int num = scanner.nextInt();
    if (num <= 1) {
      out.printf("IllegalArgument");
      System.exit(-1);
    }
    if (isPrime(num)) out.printf("true %d", --iteration);
    else out.printf("false %d", --iteration);

    scanner.close();
  }

  private static boolean isPrime(int x) {
    for (iteration = 2; iteration <= mySqrt(x); iteration++) if (x % iteration == 0) return false;
    return true;
  }

  private static double mySqrt(double x) {
    double res = 0;
    if (x < 0) {
      res = Double.NaN;
    } else if (x == 0) {
      res = 0;
    } else {
      res = x;
      for (int i = 0; i < 1000000; i++) res = (res + x / res) / 2;
    }
    return res;
  }
}
