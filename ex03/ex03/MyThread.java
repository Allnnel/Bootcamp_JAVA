package ex03;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class MyThread implements Runnable {
  private final String path;
  private final int index;
  private long i ;
  MyThread(String path, int index) {
    this.path = path;
    this.index = index;
    this.i = index;
  }

  public void run() {

    System.out.println(
        "Thread-"
            + i
            + " start download file number "
            + index);
    try {
      URL url = new URL(path);
      URLConnection connection = url.openConnection();
      String fileName = Paths.get(url.getPath()).getFileName().toString();
      InputStream in = new BufferedInputStream(connection.getInputStream());
      FileOutputStream out = new FileOutputStream(fileName);
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
      out.close();
      in.close();
      System.out.println(
          "Thread-"
              + i
              + " finish download file number "
              + index);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
