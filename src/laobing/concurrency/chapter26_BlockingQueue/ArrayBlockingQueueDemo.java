package laobing.concurrency.chapter26_BlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueDemo {
  public static void main(String[] args) {
    final BlockingQueue<Integer> abqueue = new ArrayBlockingQueue<Integer>(10);
    /* ����������:�������±���Callable */
    final Runnable runnable = new Runnable() {
      public void run() {
        try {
          /* ģ���ʱ��������Ҫ5�� */
          Thread.sleep(5000);
          /* �ڶ����з���һ�����õ��±���� */
          abqueue.put(new Random().nextInt(10000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    /* �����߳�B--�����ߣ���ȡ�±� */
    Runnable constmor_runnable = new Runnable() {
      public void run() {
        try {
          ExecutorService tPool = Executors.newCachedThreadPool();
          System.out.println("�ϰ壬���ҿ�ʼ���±�...");
          /* �����߳�A--�����ߣ����к�ʱ���������������߿�ʼ�����±� */
          for (int i = 0; i < 3; i++) {
            tPool.submit(runnable);
          }
          /* ���±� */
          System.out.println("5���Ӻ�......");
          for (int i = 0; i < 3; i++) {
            /* �����±���Ҫ5�룬��ʱ�߳����е�take()�����û���±�����˾������������ȴ��� */
            System.out.println("���±�ȯ�һ����±����ú��±���ţ�" + abqueue.take());
          }
          System.out.println("�ñ��ؼ�...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(constmor_runnable).start();
  }
}
