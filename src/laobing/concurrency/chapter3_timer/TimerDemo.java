package laobing.concurrency.chapter3_timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerDemo {
  public static void main(String[] args) {
    Timer timer = new Timer();
    MyTimerTask mytask = new MyTimerTask();
    timer.schedule(mytask, 0, 300);
    try {
       TimeUnit.SECONDS.sleep(10);
//      Thread.sleep(10000);
      /* 10���ֹͣ���� */
      mytask.cancel();// ֹͣ����ָ����TimerTask
      // timer.cancel();//ֹͣ����timer������TimerTask
      System.out.print("100%");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static class MyTimerTask extends TimerTask {
    public void run() {
      System.out.print(">");
    }
  }
}
