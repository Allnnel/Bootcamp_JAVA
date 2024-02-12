package ex01;

public class MyThread implements Runnable {
  private Print print;
  private String str;
  private int count;
  private Program.Type type;

  MyThread(String str, int count, Program.Type type, Print print) {
    Thread thread = new Thread(this, str);
    this.str = str;
    this.count = count;
    this.type = type;
    this.print = print;
    thread.start();
  }

  public void run() {
    for (int i = 0; i != count; i++) {
      try {
        print.printStr(str, type);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
