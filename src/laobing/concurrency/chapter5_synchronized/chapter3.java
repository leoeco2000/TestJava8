package laobing.concurrency.chapter5_synchronized;

/*定义外部测试类SyncLockTest*/
class SyncLockTest{
	private String lock = "lock"; 
    /*使用synchronized同步方法 */  
    public synchronized void testSyncMethod() {
        for (int i = 0; i < 100; i++) {  
            System.out.println(Thread.currentThread().getName()+":testSyncMethod:" + i);  
        }  
    }  
    /*使用synchronized同步代码块 */  
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
        /*使用synchronized方法 */  
            new Thread(new Runnable() {
                public void run() {
                    /*休眠三秒可以更好的观测错乱效果*/
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*执行*/
                    LockTest.testSyncMethod();
                }
            }).start();
            /*使用synchronized代码块 */ 
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*执行*/
                    LockTest.testSyncBlock();
                }
            }).start();
    }

}