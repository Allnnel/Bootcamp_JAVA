package ex01;

import static java.lang.System.out;

public class Print {
  private int flag = 1;

  synchronized void printStr(String str, Program.Type type) throws InterruptedException {
    while ((type == Program.Type.CONSUMER && flag == 0) || (type == Program.Type.PRODUCER && flag == 1)) {
      wait();
    }
    flag = type == Program.Type.CONSUMER ? 0 : 1;
    out.println(str);
    notify();
  }
}