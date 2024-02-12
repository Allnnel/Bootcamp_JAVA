package ex03;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scannerGrades = new Scanner(System.in);
    Scanner scannerWeek = new Scanner(System.in);
    String strGrades = "", strWeek = "", scanGrades = "";
    int flagWeek = 0, week = 0;
    for (int i = 0; i != 18; i++) {
      out.printf("-> Week ");
      week = scannerWeek.nextInt();
      if (week == 42) break;
      if (flagWeek >= week) {
        out.printf("IllegalArgument");
        System.exit(-1);
      }
      strWeek = updateStrWithInfo(strWeek, Integer.toString(week));
      scanGrades = scannerGrades.nextLine();
      if (scanGrades.equals("42")) break;
      strGrades = updateMinkWithInfo(strGrades, scanGrades);
      flagWeek = week;
    }
    graphPrint(strWeek, strGrades);
    scannerGrades.close();
    scannerWeek.close();
  }

  private static String updateStrWithInfo(String original, String toAppend) {
    return original + toAppend;
  }

  private static String updateMinkWithInfo(String original, String toAppend) {
    char number = toAppend.charAt(0);
    for (int i = 1; i < toAppend.length(); i++) {
      if (toAppend.charAt(i) != ' ' && number > toAppend.charAt(i)) number = toAppend.charAt(i);
    }
    return updateStrWithInfo(original, Character.toString(number));
  }

  private static void graphPrint(String strWeek, String strGrades) {
    for (int i = 0; i != strWeek.length(); i++) {
      out.printf("Week %c ", strWeek.charAt(i));
      for (int y = 0;
          strGrades.length() >= i + 1 && y != Character.getNumericValue(strGrades.charAt(i));
          y++) {
        out.printf("=");
      }
      out.printf(">\n");
    }
  }
}
