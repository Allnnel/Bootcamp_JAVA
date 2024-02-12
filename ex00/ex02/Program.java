package ex02;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int count = 0;
    String str = "";
    while (!str.equals("42")) {
      str = scanner.next();
      if (isPrime(getDigitSum(str))) count++;
    }
    out.printf("Count of coffee-request – %d", count);
    scanner.close();
  }

  private static boolean isPrime(int x) {
    for (int iteration = 2; iteration <= mySqrt(x); iteration++)
      if (x % iteration == 0) return false;
    return true;
  }

  private static int getDigitSum(String str) {
    int res = 0;
    for (int i = 0; i != str.length(); i++) {
      res += Character.getNumericValue(str.charAt(i));
    }
    return res;
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
