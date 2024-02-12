package enemy;

import goal.Goal;
import java.util.List;
import obstacle.Obstacle;
import player.Player;
import player.PlayerTouchTheEnemyException;

public class EnemyManager {

  private final EnemyGenerate enemyGenerate;

  public EnemyManager(List<Obstacle> obstacles, int count, int size, Player player, Goal target) {
    enemyGenerate = new EnemyGenerate(obstacles, count, size, player, target);
  }

  public List<Enemy> getEnemies() {
    return enemyGenerate.getEnemies();
  }

  public int getX(int i) {
    return enemyGenerate.getEnemies().get(i).getX();
  }

  public int getY(int i) {
    return enemyGenerate.getEnemies().get(i).getY();
  }

  public void run() throws PlayerTouchTheEnemyException {
    enemyGenerate.run();
  }
}
