package edu.school21.exceptions;

public class EntityNotFoundException extends Exception {
  public EntityNotFoundException() {
    super("the user with the specified login name was not found");
  }
}
