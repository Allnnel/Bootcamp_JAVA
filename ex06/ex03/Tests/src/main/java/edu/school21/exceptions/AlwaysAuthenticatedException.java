package edu.school21.exceptions;

public class AlwaysAuthenticatedException extends Exception {
  public AlwaysAuthenticatedException() {
    super("authentication has been completed");
  }
}
