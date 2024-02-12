package ex01;

import static java.lang.System.out;

public class Program {
  public static void main(String[] args) {

    for (int i = 0; i != 20; i++) {
      User oneUser = new User("Viktoria", i);
      out.println(
          i
              + ") UserId = "
              + oneUser.getIdentifier()
              + ", UserBalance = "
              + oneUser.getBalance()
              + ", UserName = "
              + oneUser.getName());
    }
  }
}
