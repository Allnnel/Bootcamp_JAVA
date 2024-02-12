package edu.school21.printer.app;

import static java.lang.System.out;

import edu.school21.printer.logic.Logic;
import java.io.IOException;

public class Program {
  public static void main(String[] args) {
    if (args.length != 3) return;
    String pathBMP = args[0];
    char white = args[1].charAt(0);
    char black = args[2].charAt(0);

    Logic logic = new Logic(pathBMP, black, white);
    try {
      logic.renderImageToConsole();
    } catch (IOException e) {
      out.println(e);
    }
  }
}
