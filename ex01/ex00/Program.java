package ex00;

import static java.lang.System.out;

import ex01.User;

public class Program {
  public static void main(String[] args) {
    User oneUser = new User("a", 15);
    out.println(oneUser.getName());
    out.println(oneUser.getIdentifier());
    out.println(oneUser.getBalance());
  }
}
