package edu.school21.numbers;

public class IllegalNumberException extends Throwable {
  public IllegalNumberException() {
    super("number >= 2.");
  }
}
