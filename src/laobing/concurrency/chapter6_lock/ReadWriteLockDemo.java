package laobing.concurrency.chapter6_lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        /* ��ʼ����������Ҫд������ */
        if (!cacheValid) {
            // �ڻ�ȡд��ǰ�ͷŶ���
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                /* ���¼��״̬����Ϊ�����̺߳ܿ�������֮ǰ��ȡ��д��������д������ */
                if (!cacheValid) {
                    System.out.println(Thread.currentThread().getName()
                            + ": ����δ��ʼ��");
                    data = getData();
                    cacheValid = true;
                    System.out.println(Thread.currentThread().getName()
                            + ": �����ʼ��ɣ���ǰֵ��" + data);
                }
                /* �����������ͷ�д��ǰ��ȡ���� */
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); /* �ͷ�д������Ȼ���ֶ��� */
            }
        }
        /* ��ʱ����Ȼ���ڶ��� */
        try {
            System.out.println(Thread.currentThread().getName() + ": ��ȡ����ֵΪ��"+ data);
        } finally {
            /* �ͷŶ��� */
            rwl.readLock().unlock();
        }
    }
    int getData(){
        return new Random().nextInt(100000);
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        final CachedData cachedData = new CachedData();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    cachedData.processCachedData();
                }
            }.start();
        }
    }
}