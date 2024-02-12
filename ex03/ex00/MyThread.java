package ex00;

import static java.lang.System.out;

public class MyThread implements Runnable {
  private String str;
  private int count;

  public MyThread(String str, int count) {
    Thread thread = new Thread(this, str);
    this.count = count;
    this.str = str;
    thread.start();
  }

  public void run() {
    for (int i = 0; i != count; i++) {
      out.println(str);
      try {
        Thread.sleep(0);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
