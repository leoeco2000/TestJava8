package laobing.concurrency.chapter26_BlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueDemo {
  public static void main(String[] args) {
    final BlockingQueue<Integer> abqueue = new ArrayBlockingQueue<Integer>(10);
    /* 定义生产者:用来做月饼的Callable */
    final Runnable runnable = new Runnable() {
      public void run() {
        try {
          /* 模拟耗时操作，需要5秒 */
          Thread.sleep(5000);
          /* 在队列中放上一盒做好的月饼编号 */
          abqueue.put(new Random().nextInt(10000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    /* 开启线程B--消费者：获取月饼 */
    Runnable constmor_runnable = new Runnable() {
      public void run() {
        try {
          ExecutorService tPool = Executors.newCachedThreadPool();
          System.out.println("老板，给我开始做月饼...");
          /* 启动线程A--生产者：运行耗时操作，三条生产线开始生产月饼 */
          for (int i = 0; i < 3; i++) {
            tPool.submit(runnable);
          }
          /* 拿月饼 */
          System.out.println("5秒钟后......");
          for (int i = 0; i < 3; i++) {
            /* 生产月饼需要5秒，这时线程运行到take()这里，还没有月饼，因此就在这里阻塞等待。 */
            System.out.println("用月饼券兑换到月饼，该盒月饼编号：" + abqueue.take());
          }
          System.out.println("拿饼回家...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(constmor_runnable).start();
  }
}
