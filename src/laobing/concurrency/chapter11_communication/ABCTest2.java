package laobing.concurrency.chapter11_communication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alchimie
 * 
 *         三个线程交替打印A、B、C
 */
public class ABCTest2 extends Thread {

  private final String str[] = {"A", "B", "C"};
  private final static AtomicInteger atomI = new AtomicInteger();

  public ABCTest2(String name) {
    this.setName(name);
  }

  @Override
  public void run() {
    while (true) {
      // 满10次退出打印
      if (atomI.get() / 3 == 10) {
        break;
      }
      synchronized (atomI) {
        // 顺序打印A、B、C
        if (str[atomI.get() % 3].equals(getName())) {
          atomI.getAndIncrement();
          System.out.println(getName());
          //打印一行空白
          if ("C".equals(getName())) {
            System.out.println();
          }
          // 当前线程打印打印完成后唤醒其它线程
          atomI.notifyAll();
        } else {
          // 非顺序线程wait()
          try {
            atomI.wait();
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(atomI.get());
    new ABCTest2("A").start();
    new ABCTest2("B").start();
    new ABCTest2("C").start();
  }
}
