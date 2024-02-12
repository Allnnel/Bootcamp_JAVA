package goal;

import java.util.Random;

public class Goal {
  private int targetX;
  private int targetY;
  private final int fieldLength;

  public Goal(int fieldLength) {
    this.fieldLength = fieldLength;
    positionTheTarget();
  }

  private void positionTheTarget() {
    Random random = new Random();
    targetX = random.nextInt(fieldLength);
    targetY = random.nextInt(fieldLength);
  }

  public int getTargetX() {
    return targetX;
  }

  public int getTargetY() {
    return targetY;
  }
}
