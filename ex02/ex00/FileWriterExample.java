package ex00;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
  String filePath = "/Users/rhogoron/Java_Bootcamp.Day02-1/src/ex00/result.txt";

  public void writeStringToFile(String str) {
    try {
      BufferedWriter file = new BufferedWriter(new FileWriter(filePath, true));
      file.write(str);
      if (!str.equals("")) file.newLine();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
