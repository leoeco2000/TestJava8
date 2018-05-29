package laobing.concurrency.chapter11_communication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alchimie
 * 
 *         �����߳̽����ӡA��B��C
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
      // ��10���˳���ӡ
      if (atomI.get() / 3 == 10) {
        break;
      }
      synchronized (atomI) {
        // ˳���ӡA��B��C
        if (str[atomI.get() % 3].equals(getName())) {
          atomI.getAndIncrement();
          System.out.println(getName());
          //��ӡһ�пհ�
          if ("C".equals(getName())) {
            System.out.println();
          }
          // ��ǰ�̴߳�ӡ��ӡ��ɺ��������߳�
          atomI.notifyAll();
        } else {
          // ��˳���߳�wait()
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
