package gameLogic;

import static java.lang.System.exit;
import static java.lang.System.out;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import enemy.Enemy;
import enemy.EnemyManager;
import goal.Goal;
import java.util.Scanner;
import obstacle.Obstacle;
import obstacle.ObstacleManager;
import player.Player;

public class ProcessGame {
  private char[][] field;
  private final Goal target;
  private final Player player;
  private final ObstacleManager obstacle;
  private final EnemyManager enemy;

  private final ConfigReader configReader;
  private final boolean isProduction;

  public ProcessGame(int size, int enemiesCount, int wallsCount, boolean isProduction) {
    configReader = new ConfigReader(isProduction);
    field = new char[size][size];
    target = new Goal(size);
    player = new Player(size, target);
    obstacle = new ObstacleManager(wallsCount, size, player, target);
    enemy = new EnemyManager(obstacle.getObstacles(), enemiesCount, size, player, target);
    this.isProduction = isProduction;
    startGame();
  }

  private void startGame() {
    Scanner sc = new Scanner(System.in);
    initializationField();
    while (true) {
      try {
        String ch = sc.nextLine();
        if (ch.length() != 1) {
          out.println("Incorrect direction");
          continue;
        }
        if (ch.charAt(0) == '9') {
          throw new PlayerGaveUpException();
        } else if (!"wasd".contains(String.valueOf(ch.charAt(0)))) {
          out.println("Incorrect direction");
          continue;
        }
        int res = player.movePlayer(ch.charAt(0), field);
        if (res == 1) {
          out.println("You win!!!");
          exit(-1);
        } else if (res == 0) {
          out.println("You can't go in this direction");
          continue;
        }
        initializationField();
        out.println();
        if (!isProduction) {
          out.println("Press '8' to confirm enemy move:");
          String confirmation = sc.nextLine();
          if (!confirmation.equals("8")) {
            out.println("You didn't confirm enemy move. Game over.");
            exit(-1);
          }
        }
        out.println("Enemy move:");
        enemy.run();
      } catch (Exception e) {
        out.println(e.getMessage());
        exit(-1);
      }
      initializationField();
    }
  }

  private void initializationField() {
    ColoredPrinter cp = new ColoredPrinter.Builder(0, false).build();
    char symbol;
    for (int x = 0; x < field.length; x++) {
      for (int y = 0; y < field.length; y++) {
        if (x == player.getPlayerX() && y == player.getPlayerY()) {
          symbol = configReader.getSymbol("player").charAt(0);
          field[x][y] = symbol;
          printSymbol(cp, symbol, configReader.getColor("player"));
        } else if (x == target.getTargetX() && y == target.getTargetY()) {
          symbol = configReader.getSymbol("goal").charAt(0);
          field[x][y] = symbol;
          printSymbol(cp, symbol, configReader.getColor("goal"));
        } else if (fieldSymbol(x, y)) {
          symbol = configReader.getSymbol("wall").charAt(0);
          field[x][y] = symbol;
          printSymbol(cp, symbol, configReader.getColor("wall"));
        } else if (enemiesSymbol(x, y)) {
          symbol = configReader.getSymbol("enemy").charAt(0);
          field[x][y] = symbol;
          printSymbol(cp, symbol, configReader.getColor("enemy"));
        } else {
          if (configReader.getSymbol("empty").isEmpty()) {
            symbol = ' ';
          } else {
            symbol = configReader.getSymbol("empty").charAt(0);
          }
          field[x][y] = symbol;
          printSymbol(cp, symbol, configReader.getColor("empty"));
        }
      }
      out.println();
    }
  }

  private void printSymbol(ColoredPrinter cp, char symbol, String color) {
    cp.setBackgroundColor(Ansi.BColor.valueOf(color));
    cp.print(symbol);
  }

  private boolean fieldSymbol(int x, int y) {

    for (Obstacle obstacle : obstacle.getObstacles()) {
      if (obstacle.getY() == y && obstacle.getX() == x) {
        return true;
      }
    }
    return false;
  }

  private boolean enemiesSymbol(int x, int y) {

    for (Enemy enemy : enemy.getEnemies()) {
      if (enemy.getY() == y && enemy.getX() == x) {
        return true;
      }
    }
    return false;
  }
}
