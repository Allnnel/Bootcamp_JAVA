package edu.school21.printer.app;

import static java.lang.System.out;

import edu.school21.printer.logic.Logic;
import java.io.IOException;

public class Program {

  private static String white;
  private static String black;
  private static String pathBMP;

  public static void main(String[] args) {
    if (args.length != 3) return;
    getColorFromArgs(args);
    Logic logic = new Logic(pathBMP, white, black);
    try {
      logic.renderImageToConsole();
    } catch (IOException e) {
      out.println(e);
    }
  }

  private static void getColorFromArgs(String[] args) {
    pathBMP = args[0];
    for (int i = 1; i < args.length; i++) {
      String[] parts = args[i].split("=");
      if (parts.length == 2) {
        String colorName = parts[1].toUpperCase();
        if (args[i].startsWith("--white=")) {
          white = colorName;
        } else if (args[i].startsWith("--black=")) {
          black = colorName;
        }
      }
    }
  }
}
