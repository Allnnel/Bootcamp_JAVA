package ex00;

import static java.lang.System.out;

import java.io.*;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class SignatureAnalyzer {
  private static final Map<String, String> FILE_FORMATS = new HashMap<>();
  private static String filePath;

  static {
    loadFileFormats("/Users/rhogoron/Java_Bootcamp.Day02-1/src/ex00/signatures.txt");
  }

  public SignatureAnalyzer(String path) {
    this.filePath = path;
  }

  public static String detectFormat() {
    try {
      FileInputStream inputStream = new FileInputStream(filePath);
      byte[] headerBytes = new byte[8];
      int bytesRead = inputStream.read(headerBytes);
      if (bytesRead == 8) {
        String hexHeader = bytesToHex(headerBytes);
        return FILE_FORMATS.getOrDefault(hexHeader, "");
      }
    } catch (IOException e) {
      out.println(e);
    }
    return "";
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder hexStringBuilder = new StringBuilder();
    for (byte b : bytes) {
      hexStringBuilder.append(String.format("%02X", b));
    }
    return hexStringBuilder.toString();
  }

  private static void loadFileFormats(String filePath) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line = null;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length == 2) {
          String fileType = parts[0];
          String hexSignature = parts[1].replace(" ", "");
          FILE_FORMATS.put(hexSignature, fileType);
        }
      }
    } catch (IOException e) {
      out.println(e);
    }
  }
}
