package obstacle;

import goal.Goal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import player.Player;

public class ObstacleGenerate {
  private final Player player;
  private final Goal goal;
  private final List<Obstacle> obstacles;
  private final int count;
  private final int size;
  private final Random random;

  public ObstacleGenerate(int count, int size, Player player, Goal goal) {
    this.obstacles = new ArrayList<>();
    this.size = size;
    this.count = count;
    this.player = player;
    this.goal = goal;
    this.random = new Random();
    generateObstacles();
  }

  /**
   * Метод генерирует указанное количество препятствий и добавляет их в список препятствий.
   * Препятствия не могут быть сгенерированы на тех же местах, где уже находятся игрок, враги или
   * другие препятствия.
   */
  private void generateObstacles() {
    for (int i = 0; i < count; i++) {
      Obstacle obstacle = generateRandomObstacle();
      while (checkCollision(obstacle)) {
        obstacle = generateRandomObstacle();
      }
      obstacles.add(obstacle);
    }
  }

  /**
   * Метод проверяет наличие коллизий для указанного препятствия. Коллизии могут произойти с другими
   * препятствиями, игроком или целью.
   *
   * @param obstacle Препятствие, для которого проверяются коллизии.
   * @return true, если обнаружена коллизия, в противном случае - false.
   */
  private boolean checkCollision(Obstacle obstacle) {
    for (Obstacle existingObstacle : obstacles) {
      if (obstacle.getX() == existingObstacle.getX()
          && obstacle.getY() == existingObstacle.getY()) {
        return true;
      }
    }
    if (obstacle.getX() == player.getPlayerX() && obstacle.getY() == player.getPlayerY()) {
      return true;
    }
    if (obstacle.getX() == goal.getTargetX() && obstacle.getY() == goal.getTargetY()) {
      return true;
    }
    if (!obstacles.isEmpty()) {
      for (Obstacle existingObstacle : obstacles) {
        if (obstacle.getX() == existingObstacle.getX()
            && obstacle.getY() == existingObstacle.getY()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Метод генерирует случайное препятствие. Препятствие не может быть сгенерировано на месте, где
   * находятся слишком близкие соседние объекты.
   *
   * @return Случайно сгенерированное препятствие.
   */
  private Obstacle generateRandomObstacle() {

    int x = random.nextInt(size - 2) + 1;
    int y = random.nextInt(size - 2) + 1;

    if (checkNeighbors(x, y)) {
      while ((validateAdjacentCells(x, y))) {
        y = random.nextInt(size - 2) + 1;
      }
    }

    return new Obstacle(x, y);
  }

  /**
   * Метод проверяет наличие соседних препятствий по указанным координатам. Если находится
   * препятствие с тем же значением координаты X, метод возвращает true.
   *
   * @param x Координата X для проверки соседних препятствий.
   * @param y Координата Y для проверки соседних препятствий (не используется в данном методе).
   * @return true, если обнаружено соседнее препятствие с тем же значением координаты X, в противном
   *     случае - false.
   */
  private boolean checkNeighbors(int x, int y) {
    for (Obstacle existingObstacle : obstacles) {
      if (x == existingObstacle.getX()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Метод проверяет соседние клетки от указанных координат на наличие препятствий. Если соседняя
   * клетка имеет препятствие, метод возвращает false, иначе - true.
   *
   * @param x Координата X для проверки соседних клеток.
   * @param y Координата Y для проверки соседних клеток.
   * @return true, если соседние клетки свободны от препятствий, в противном случае - false.
   */
  private boolean validateAdjacentCells(int x, int y) {
    for (Obstacle existingObstacle : obstacles) {
      if (x == existingObstacle.getX() && (y + 1) == existingObstacle.getY()) {
        return false;
      } else if (x == existingObstacle.getX() && (y - 1) == existingObstacle.getY()) {
        return false;
      }
    }
    return true;
  }

  public List<Obstacle> getObstacles() {
    return obstacles;
  }
}
