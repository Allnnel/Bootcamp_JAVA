package ex02;

public class UserNotFoundException extends Exception {
  public UserNotFoundException() {
    super("User not found!");
  }
}
