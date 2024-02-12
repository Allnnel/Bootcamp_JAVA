package gameLogic;

public class PlayerGaveUpException extends Exception {
  public PlayerGaveUpException() {
    super("Player gave up");
  }
}
