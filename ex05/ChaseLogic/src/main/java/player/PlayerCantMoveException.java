package player;

public class PlayerCantMoveException extends Exception {
  public PlayerCantMoveException() {
    super("The moves are over");
  }
}
