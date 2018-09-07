package laobing.concurrency.chapter21_Condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

  static class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();// 存数据的线程等待集——因为满了而等待
    final Condition notEmpty = lock.newCondition();// 取数据的线程等待集——因为空了而等待
    /* 用数组实现缓冲队列(FIFO) */
    final Object[] items = new Object[5];
    /*
     * putptr:存数据的索引位置 takptr:取数据的索引位置 count:当前队列大小
     */
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
      lock.lock();/* 给需要同步的代码加锁 */
      try {
        /* 如果数组满了，当前线程进入存数据的等待集。不用if是怕线程假醒 */
        while (count == items.length) {
          System.err.println(Thread.currentThread().getName() + "写入数据[" + putptr + "]异常:缓冲池已满");
          notFull.await();
        }
        /* 数据写入 */
        System.out.println(
            Thread.currentThread().getName() + "写入数据[" + putptr + "]:" + String.valueOf(x));
        items[putptr] = x;
        /* 改变写入索引位置+1（为下次写入数据准备），如果超出数组边界，则重置为数组起始点 */
        if (++putptr == items.length)
          putptr = 0;
        /* 增加数组有效果元素计数，即：队列大小加1 */
        ++count;
        /* 至此队列中肯定有可用数据了，从“取数据的线程等待集”中唤醒线程 */
        notEmpty.signal();
      } finally {
        /* lock 必须在 finally 块中释放。否则，如果受保护的代码将抛出异常，锁就有可能永远得不到释放 */
        lock.unlock();
      }
    }

    public Object take() throws InterruptedException {
      lock.lock();/* 给需要同步的代码加锁 */
      try {
        /* 如果数组为空，当前线程进入读数据的等待集。不用if是怕线程假醒 */
        System.err.println("线程预备");
        while (count == 0) {
          System.err.println(Thread.currentThread().getName() + "读取数据[" + putptr + "]异常:缓冲池为空");
          notEmpty.await();
        }
        System.err.println("线程开始读数据");
        /* 读取数据 */
        Object x = items[takeptr];
        System.out.println(Thread.currentThread().getName() + "获取队列数据[" + takeptr + "]:" + x);
        /* 改变读取索引位置+1（为下次读数据准备），如果超出数组边界，则重置为数组起始点 */
        if (++takeptr == items.length)
          takeptr = 0;
        /* 减少数组有效果元素计数，即：队列大小减1 */
        --count;
        /* 至此队列中肯定有可用空间了，从“存数据的线程等待集”中唤醒线程 */

        notFull.signal();
        return x;
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    final BoundedBuffer boundbuffe = new BoundedBuffer();

    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(new Random().nextInt(3000));
            int temp = new Random().nextInt(1000);
            boundbuffe.put(temp);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
    for (int i = 0; i < 5; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(new Random().nextInt(500));
            boundbuffe.take();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

  }
}
