package laobing.concurrency.chapter6_lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readwritelocklianxi {
    public static void main(String[] args) {
        final cacheObj cachedData = new cacheObj();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    cachedData.setCache();
                }
            }.start();
        }
    }
}


class cacheObj{
	Object cache;
	volatile boolean cacheValid;
	final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	int getCache(){
		return new Random().nextInt(100000);
	}
	
	void setCache() {
		rwl.readLock().lock();
		if(!cacheValid) {
			try {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				System.out.println(rwl.writeLock().getHoldCount());
				System.out.println(rwl.writeLock().isHeldByCurrentThread());
				System.out.println(rwl.isWriteLockedByCurrentThread());
				System.out.println(Thread.currentThread().holdsLock(rwl));
	            System.out.println(Thread.currentThread().getName()
	                    + ": ����δ��ʼ��");
	            cache = getCache();
	            cacheValid = true;
	            System.out.println(Thread.currentThread().getName()
	                    + ": �����ʼ��ɣ���ǰֵ��" + cache);
	            rwl.readLock().lock();
			} finally {
	            rwl.writeLock().unlock();
			}
		}
		try {
            System.out.println(Thread.currentThread().getName() + ": ��ȡ����ֵΪ��"+ cache);
		} finally {
			rwl.readLock().unlock();
		}
	}
}