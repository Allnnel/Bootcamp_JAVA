package org.example;

import static java.lang.System.exit;
import static java.lang.System.out;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import gameLogic.ProcessGame;

@Parameters(separators = "=")
public class Main {
  @Parameter(names = "--enemiesCount", required = true)
  private static int enemiesCount;

  @Parameter(names = "--wallsCount", required = true)
  private static int wallsCount;

  @Parameter(names = "--size", required = true)
  private static int size;

  @Parameter(names = "--profile", required = true)
  private static String profile;

  public static void main(String[] args) {

    Main main = new Main();
    JCommander.newBuilder().addObject(main).build().parse(args);
    int maxWalls = (size * size) / 3;
    int maxEnemies = (size * size) / maxWalls;
    if ((wallsCount >= maxWalls && enemiesCount >= maxEnemies)
        || (!profile.equals("dev") && !profile.equals("production"))) {
      out.println("Incorrect values");
      exit(-1);
    } else {
      boolean isProduction = profile.equals("production");
      ProcessGame game = new ProcessGame(size, enemiesCount, wallsCount, isProduction);
    }
  }
}
