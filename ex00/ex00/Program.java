package ex00;

import static java.lang.System.out;

public class Program {
  public static void main(String[] args) {
    int input = 479598;
    out.printf("%d", sum(input));
  }

  private static int sum(int num) {
    int res = 0;
    for (int i = 0; i != 6; i++) {
      res += num % 10;
      num /= 10;
    }
    return res;
  }
}
