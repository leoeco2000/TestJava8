package laobing.concurrency.chapter21_Condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

  static class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();// �����ݵ��̵߳ȴ���������Ϊ���˶��ȴ�
    final Condition notEmpty = lock.newCondition();// ȡ���ݵ��̵߳ȴ���������Ϊ���˶��ȴ�
    /* ������ʵ�ֻ������(FIFO) */
    final Object[] items = new Object[5];
    /*
     * putptr:�����ݵ�����λ�� takptr:ȡ���ݵ�����λ�� count:��ǰ���д�С
     */
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
      lock.lock();/* ����Ҫͬ���Ĵ������ */
      try {
        /* ����������ˣ���ǰ�߳̽�������ݵĵȴ���������if�����̼߳��� */
        while (count == items.length) {
          System.err.println(Thread.currentThread().getName() + "д������[" + putptr + "]�쳣:���������");
          notFull.await();
        }
        /* ����д�� */
        System.out.println(
            Thread.currentThread().getName() + "д������[" + putptr + "]:" + String.valueOf(x));
        items[putptr] = x;
        /* �ı�д������λ��+1��Ϊ�´�д������׼�����������������߽磬������Ϊ������ʼ�� */
        if (++putptr == items.length)
          putptr = 0;
        /* ����������Ч��Ԫ�ؼ������������д�С��1 */
        ++count;
        /* ���˶����п϶��п��������ˣ��ӡ�ȡ���ݵ��̵߳ȴ������л����߳� */
        notEmpty.signal();
      } finally {
        /* lock ������ finally �����ͷš���������ܱ����Ĵ��뽫�׳��쳣�������п�����Զ�ò����ͷ� */
        lock.unlock();
      }
    }

    public Object take() throws InterruptedException {
      lock.lock();/* ����Ҫͬ���Ĵ������ */
      try {
        /* �������Ϊ�գ���ǰ�߳̽�������ݵĵȴ���������if�����̼߳��� */
        System.err.println("�߳�Ԥ��");
        while (count == 0) {
          System.err.println(Thread.currentThread().getName() + "��ȡ����[" + putptr + "]�쳣:�����Ϊ��");
          notEmpty.await();
        }
        System.err.println("�߳̿�ʼ������");
        /* ��ȡ���� */
        Object x = items[takeptr];
        System.out.println(Thread.currentThread().getName() + "��ȡ��������[" + takeptr + "]:" + x);
        /* �ı��ȡ����λ��+1��Ϊ�´ζ�����׼�����������������߽磬������Ϊ������ʼ�� */
        if (++takeptr == items.length)
          takeptr = 0;
        /* ����������Ч��Ԫ�ؼ������������д�С��1 */
        --count;
        /* ���˶����п϶��п��ÿռ��ˣ��ӡ������ݵ��̵߳ȴ������л����߳� */

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
