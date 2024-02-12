package player;

import goal.Goal;
import java.util.Random;

public class Player {
  private int playerX;
  private int playerY;
  private final int fieldLength;
  private final Goal target;

  public Player(int fieldLength, Goal target) {
    this.fieldLength = fieldLength;
    this.target = target;
    firstPositionThePlayer();
  }

  private void firstPositionThePlayer() {
    Random random = new Random();
    playerX = random.nextInt(fieldLength);
    playerY = random.nextInt(fieldLength);
    while (playerX == target.getTargetX() && playerY == target.getTargetY()) {
      playerX = random.nextInt(fieldLength);
      playerY = random.nextInt(fieldLength);
    }
  }

  public int movePlayer(char direction, char[][] field)
      throws PlayerTouchTheEnemyException, PlayerCantMoveException {
    int stage = 0;
    if (!canMove(field)) {
      throw new PlayerCantMoveException();
    }
    switch (Character.toLowerCase(direction)) {
      case 'w':
        stage = processMovePlayer(playerX - 1, playerY, field);
        break;
      case 'a':
        stage = processMovePlayer(playerX, playerY - 1, field);
        break;
      case 's':
        stage = processMovePlayer(playerX + 1, playerY, field);
        break;
      case 'd':
        stage = processMovePlayer(playerX, playerY + 1, field);
        break;
      default:
    }

    return stage;
  }

  private int processMovePlayer(int newX, int newY, char[][] field)
      throws PlayerTouchTheEnemyException {
    int stage = 0;
    if (newX >= 0 && newX < fieldLength && newY >= 0 && newY < fieldLength) {
      char newPosition = field[newX][newY];

      if (newPosition == 'x') {
        throw new PlayerTouchTheEnemyException();
      } else if (newPosition == 'O') {
        stage = 1;
      } else if (newPosition != '#') {
        playerX = newX;
        playerY = newY;
        stage = -1;
      }
    }
    return stage;
  }

  private boolean canMove(char[][] field) {
    String ch = " O";
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    if (playerX > 0) {
      up = ch.contains(String.valueOf(field[playerX - 1][playerY]));
    }
    if (playerX + 1 < fieldLength) {
      down = ch.contains(String.valueOf(field[playerX + 1][playerY]));
    }
    if (playerY > 0) {
      left = ch.contains(String.valueOf(field[playerX][playerY - 1]));
    }
    if (playerY + 1 < fieldLength) {
      right = ch.contains(String.valueOf(field[playerX][playerY + 1]));
    }
    return up || down || right || left;
  }

  public int getPlayerX() {
    return playerX;
  }

  public int getPlayerY() {
    return playerY;
  }
}
