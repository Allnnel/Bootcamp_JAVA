package ex03;

public class TransactionNotFoundException extends Exception {
  public TransactionNotFoundException() {
    super("User not found!");
  }
}
