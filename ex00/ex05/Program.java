package ex05;

import static java.lang.System.out;

import java.util.Scanner;

public class Program {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] nameStudent = new String[10];
    String[] timetable = new String[6 * 10];
    String[] attendance = new String[10 * 6 * 10];

    nameStudent = scanStr(nameStudent, scanner);
    timetable = scanStr(timetable, scanner);
    attendance = scanStr(attendance, scanner);

    if (nameStudent.length != 0 && timetable.length != 0)
      printTimeTable(nameStudent, timetable, attendance);

    scanner.close();
  }

  public static String[] scanStr(String[] str, Scanner scanner) {
    int count = 0;
    for (int i = 0; i != str.length; i++) {
      str[i] = scanner.nextLine();
      if (str[i].equals(".")) break;
      count++;
      if (i + 1 == str.length) out.println("incremental limit");
    }
    String[] result = new String[count];
    arrayCopy(str, result, count);
    return result;
  }

  public static void arrayCopy(String[] src, String[] dest, int length) {
    if (src == null || dest == null || length < 0 || length > src.length || length > dest.length)
      System.exit(-1);
    for (int i = 0; i < length; i++) dest[i] = src[i];
  }

  public static void printTimeTable(String[] nameStudent, String[] timetable, String[] attendance) {
    weekSort(timetable);
    int time = 0, indexWeek = 0;
    String[] daysWeek = new String[(timetable.length * 4) + timetable.length];
    String[] timeWeek = new String[(timetable.length * 4) + timetable.length];
    out.printf("%-10s", "");
    for (int i = 0; i < timetable.length * 4; i++) {
      for (int y = 0; y < timetable.length; y++) {
        int day = dayToDay(timetable, y, 1);

        int spaceIndex = timetable[y].indexOf(" ");
        if (spaceIndex > 0) time = timetable[y].charAt(spaceIndex - 1) - '0';

        if (i != 0) day = (day + 7 * i) + 1;
        else day += 1;

        if (day < 32) {
          String dayString = String.valueOf(day);
          daysWeek[indexWeek] = cutAtSpace(dayString, 1);
          String timeString = String.valueOf(time);
          timeWeek[indexWeek] = cutAtSpace(timeString, 1);
          out.printf(
              "%-" + (12 - timetable[y].length() - Integer.toString(day).length()) + "s",
              timetable[y]);
          out.printf("%d|", day);
          indexWeek++;
        }
      }
    }

    out.println();

    for (int i = 0; i != nameStudent.length && nameStudent[i] != null; i++) {
      if (nameStudent[i].length() <= 10) {
        out.printf("%-" + (10) + "s", nameStudent[i]);
      }

      for (int y = 0; y != daysWeek.length - 1; y++) {
        boolean attendanceFound = false;
        for (int z = 0; z != attendance.length; z++) {
          String name = cutAtSpace(attendance[z], 1);
          String name1 = attendance[z].split(" ")[1].trim();
          String name2 = attendance[z].split(" ")[2].trim();
          String name3 = attendance[z].split(" ")[3].trim();
          if (name.equals(nameStudent[i])
              && name2.equals(daysWeek[y])
              && name1.equals(timeWeek[y])) {
            if (name3.equals("HERE")) {
              out.printf("%-7s", "");
              out.printf("1|");
            } else {
              out.printf("%-6s", "");
              out.printf("-1|");
            }
            attendanceFound = true;
            break;
          }
        }

        if (!attendanceFound && y + 1 != daysWeek.length - 1) {
          out.printf("%-8s", "");
          out.print("|");
        }
      }
      out.println();
    }
  }

  public static String cutAtSpace(String input, int spaceCount) {
    if (input == null || input.isEmpty() || spaceCount <= 0) return null;
    int currentSpace = 0;
    String output = "";
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (currentChar == ' ') currentSpace++;
      if (currentSpace == spaceCount) break;
      output += currentChar;
    }
    return output;
  }

  public static void weekSort(String[] timetable) {
    for (int y = 0; y < timetable.length - 1; y++) {
      if (dayToDay(timetable, y, 1) > dayToDay(timetable, y + 1, 1)) {
        String tmp = timetable[y];
        timetable[y] = timetable[y + 1];
        timetable[y + 1] = tmp;
      }
    }
  }

  private static int indexWeekSort(String day) {
    String[] daysOfWeek = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
    for (int i = 0; i < daysOfWeek.length; i++) {
      if (daysOfWeek[i].equals(day)) return i;
    }
    return -1;
  }

  public static int dayToDay(String[] timetable, int numberWeek, int index) {
    int day = 0;
    String[] parts = timetable[numberWeek].split(" ");
    if (parts.length > index) {
      day = indexWeekSort(parts[index]);
    }
    return day;
  }
}
