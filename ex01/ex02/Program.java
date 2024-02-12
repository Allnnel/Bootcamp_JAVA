package ex02;

import static java.lang.System.out;

import ex01.User;

public class Program {
  public static void main(String[] args) {

    UsersArrayList UserList = new UsersArrayList();
    try {
      for (int i = 0; i != 10; i++) {
        User User = new User("Marina", 100);
        UserList.addUser(User);
        out.println("UserById == " + UserList.getUserById(i + 1));
        out.println("UserByIndex == " + UserList.getUserByIndex(i));
        out.println("getUserCount == " + UserList.getUserCount());
      }
      UserList.getUserByIndex(12);
    } catch (UserNotFoundException e) {
      out.println("error: " + e);
    }
  }
}
