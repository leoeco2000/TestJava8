package laobing.concurrency.chapter5_synchronized;

/*�����ⲿ������SyncLockTest*/
class SyncLockTest{
	private String lock = "lock"; 
    /*ʹ��synchronizedͬ������ */  
    public synchronized void testSyncMethod() {
        for (int i = 0; i < 100; i++) {  
            System.out.println(Thread.currentThread().getName()+":testSyncMethod:" + i);  
        }  
    }  
    /*ʹ��synchronizedͬ������� */  
    public void testSyncBlock() {  
        synchronized (lock) { 
            for (int i = 0; i < 100; i++) {  
                System.out.println(Thread.currentThread().getName()+":testSyncBlock:" + i);  
            }  
        }  
    }  
}
public class chapter3 {
    public static void main(String[] args) {
        final SyncLockTest LockTest=new SyncLockTest();
        /*ʹ��synchronized���� */  
            new Thread(new Runnable() {
                public void run() {
                    /*����������Ը��õĹ۲����Ч��*/
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*ִ��*/
                    LockTest.testSyncMethod();
                }
            }).start();
            /*ʹ��synchronized����� */ 
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*ִ��*/
                    LockTest.testSyncBlock();
                }
            }).start();
    }

}