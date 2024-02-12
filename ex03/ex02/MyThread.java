package ex02;

import static java.lang.System.out;

public class MyThread implements Runnable {
  int[] array;
  int indexStart;
  int indexEnd;
  int sum;
  int index;

  MyThread(int[] array, int indexStart, int indexEnd, int index) {
    Thread thread = new Thread(this, "MyThread");
    sum = 0;
    this.array = array;
    this.indexStart = indexStart;
    this.indexEnd = indexEnd;
    this.index = index;
    thread.start();
  }

  public void run() {
    for (int i = indexStart; i != indexEnd + 1; i++) {
      sum += array[i];
    }
    out.println("Thread " + index + ": from " + indexStart + " to " + indexEnd + " sum is " + sum);
  }
}
