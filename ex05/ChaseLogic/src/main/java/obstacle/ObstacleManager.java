package obstacle;

import goal.Goal;
import java.util.List;
import player.Player;

public class ObstacleManager {
  private final ObstacleGenerate obstacleGenerate;

  public ObstacleManager(int count, int size, Player player, Goal goal) {
    obstacleGenerate = new ObstacleGenerate(count, size, player, goal);
  }

  public List<Obstacle> getObstacles() {
    return obstacleGenerate.getObstacles();
  }

  public int getX(int i) {
    return obstacleGenerate.getObstacles().get(i).getX();
  }

  public int getY(int i) {
    return obstacleGenerate.getObstacles().get(i).getY();
  }
}
