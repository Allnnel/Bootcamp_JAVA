package ex02;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  public static void main(String args[]) {

    if (args.length == 0 || !args[0].equals("--current-folder=")) {
      out.println("no path file");
      return;
    }
    String filePath = args[1];
    out.println(filePath);
    ConsoleCommandExecutor consoleCommandExecutor = new ConsoleCommandExecutor(filePath);

    while (true) {
      Scanner scanner = new Scanner(System.in);
      String command = scanner.nextLine();
      if (command.equals("exit")) break;
      String[] tokens = command.split("\\s+");

      if (tokens[0].equals("ls")) {
        consoleCommandExecutor.listFilesAndFolders();
      } else if (tokens[0].equals("cd") && tokens.length == 2) {
        consoleCommandExecutor.changeDirectory(tokens[1]);
      } else if (tokens[0].equals("mv") && tokens.length == 3) {
        consoleCommandExecutor.moveFileOrRename(tokens[1], tokens[2]);
      } else {
        out.println("invalid command");
      }
    }
  }
}
