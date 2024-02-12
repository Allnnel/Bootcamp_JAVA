package enemy;

import goal.Goal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import obstacle.Obstacle;
import player.Player;
import player.PlayerTouchTheEnemyException;

public class EnemyGenerate {
  Goal goal;
  Player player;
  private final Random random;
  private final List<Enemy> enemies;
  List<Obstacle> obstacles;
  private final int count;
  private final int size;

  public EnemyGenerate(List<Obstacle> obstacles, int count, int size, Player player, Goal goal) {
    enemies = new ArrayList<>();
    random = new Random();
    this.count = count;
    this.size = size;
    this.goal = goal;
    this.player = player;
    this.obstacles = obstacles;
    generateEnemy();
  }

  /**
   * Метод генерирует указанное количество врагов и добавляет их в список врагов. Враги не могут
   * быть сгенерированы слишком близко к игроку или другим врагам.
   */
  private void generateEnemy() {
    for (int i = 0; i != count; i++) {
      Enemy enemy = generateRandomEnemy();
      while (checkCollision(enemy)) {
        enemy = generateRandomEnemy();
      }
      enemies.add(enemy);
    }
  }

  /**
   * Метод генерирует случайного врага. Враг не может быть сгенерирован слишком близко к игроку.
   *
   * @return Случайно сгенерированный враг.
   */
  private Enemy generateRandomEnemy() {
    int x, y;
    do {
      x = random.nextInt(size);
      y = random.nextInt(size);
    } while (Math.abs(x - player.getPlayerX()) <= 1 && Math.abs(y - player.getPlayerY()) <= 1);

    return new Enemy(x, y);
  }

  /**
   * Метод проверяет наличие коллизий для указанного врага. Коллизии могут произойти с другими
   * врагами, игроком, целью или препятствиями.
   *
   * @param enemy Объект врага, для которого проверяются коллизии.
   * @return true, если обнаружена коллизия, в противном случае - false.
   */
  private boolean checkCollision(Enemy enemy) {
    for (Enemy existingEnemy : enemies) {
      if (enemy.getX() == existingEnemy.getX() && enemy.getY() == existingEnemy.getY()) {
        return true;
      }
    }
    if (enemy.getX() == player.getPlayerX() && enemy.getY() == player.getPlayerY()) {
      return true;
    }
    if (enemy.getX() == goal.getTargetX() && enemy.getY() == goal.getTargetY()) {
      return true;
    }
    for (Obstacle obstacle : obstacles) {
      if (enemy.getX() == obstacle.getX() && enemy.getY() == obstacle.getY()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Метод проверяет наличие коллизий по заданным координатам для обхода противником. Коллизии могут
   * произойти с врагами, целью или препятствиями.
   *
   * @param x Координата X для проверки коллизии.
   * @param y Координата Y для проверки коллизии.
   * @return true, если обнаружена коллизия, в противном случае - false.
   */
  private boolean checkCollisionPersecution(int x, int y) {
    for (Enemy existingEnemy : enemies) {
      if (x == existingEnemy.getX() && y == existingEnemy.getY()) {
        return true;
      }
    }
    if (x == goal.getTargetX() && y == goal.getTargetY()) {
      return true;
    }
    for (Obstacle obstacle : obstacles) {
      if (x == obstacle.getX() && y == obstacle.getY()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Метод запускает движение врагов и проверяет столкновение игрока с врагами. В случае
   * столкновения выбрасывается исключение PlayerTouchTheEnemyException.
   *
   * @throws PlayerTouchTheEnemyException Исключение, выбрасываемое при столкновении игрока с
   *     врагами.
   */
  public void run() throws PlayerTouchTheEnemyException {
    for (Enemy enemy : enemies) {
      Enemy newEnemy = enemy;

      avoidObstacle(newEnemy);

      if (newEnemy.getX() == player.getPlayerX() && newEnemy.getY() == player.getPlayerY()) {
        throw new PlayerTouchTheEnemyException();
      }

      enemy.setX(newEnemy.getX());
      enemy.setY(newEnemy.getY());
    }
  }

  /**
   * Этот метод вычисляет направление движения для врага с целью избежания препятствий и приближения
   * к игроку.
   *
   * @param enemy Объект врага, для которого вычисляется избегание препятствий и приближение к
   *     игроку.
   */
  private void avoidObstacle(Enemy enemy) {
    double playerX = player.getPlayerX();
    double playerY = player.getPlayerY();

    double[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    double closestX = enemy.getX();
    double closestY = enemy.getY();
    double minDistance =
        Math.sqrt(Math.pow(closestX - playerX, 2) + Math.pow(closestY - playerY, 2));

    for (double[] direction : directions) {
      double newX = enemy.getX() + direction[0];
      double newY = enemy.getY() + direction[1];

      double distanceToPlayer =
          Math.sqrt(Math.pow(newX - playerX, 2) + Math.pow(newY - playerY, 2));

      if (!checkCollisionPersecution((int) newX, (int) newY) && distanceToPlayer < minDistance) {

        boolean obstacleOnPath = false;
        for (Obstacle obstacle : obstacles) {
          if (obstacleOnPath(closestX, closestY, newX, newY, obstacle.getX(), obstacle.getY())) {
            obstacleOnPath = true;
            break;
          }
        }

        if (!obstacleOnPath) {
          closestX = newX;
          closestY = newY;
          minDistance = distanceToPlayer;
        }
      }
    }

    enemy.setX((int) closestX);
    enemy.setY((int) closestY);
  }

  /**
   * Этот метод проверяет, лежит ли препятствие на пути от точки (startX, startY) до точки (endX,
   * endY).
   *
   * @param startX Координата X начальной точки отрезка.
   * @param startY Координата Y начальной точки отрезка.
   * @param endX Координата X конечной точки отрезка.
   * @param endY Координата Y конечной точки отрезка.
   * @param obstacleX Координата X препятствия.
   * @param obstacleY Координата Y препятствия.
   * @return true, если препятствие лежит на пути от начальной до конечной точки, в противном случае
   *     - false.
   */
  private boolean obstacleOnPath(
      double startX, double startY, double endX, double endY, double obstacleX, double obstacleY) {
    double dxc = obstacleX - startX;
    double dyc = obstacleY - startY;
    double dxl = endX - startX;
    double dyl = endY - startY;
    double cross = dxc * dyl - dyc * dxl;
    if (cross != 0) {
      return false;
    }
    if (Math.abs(dxl) >= Math.abs(dyl)) {
      return dxl > 0
          ? startX <= obstacleX && obstacleX <= endX
          : endX <= obstacleX && obstacleX <= startX;
    } else {
      return dyl > 0
          ? startY <= obstacleY && obstacleY <= endY
          : endY <= obstacleY && obstacleY <= startY;
    }
  }

  public List<Enemy> getEnemies() {
    return enemies;
  }
}
