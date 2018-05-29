package laobing.concurrency.chapter11_communication;

public class ABCTest {
    public static void main(String[] args) {
        final Communication LockTest = new Communication();
        /* 第一个线程说5次Hello */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.A();
                }
            }
        }).start();
        /* 第二个线程说5次Word */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.B();
                }
            }
        }).start();
        /* 第二个线程说5次Word */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.C();
                }
            }
        }).start();
    }
}
/* 定义外部测试类SyncLockTest */
class Communication {
    /*
     * 为了增加代码可读性，这里用了两个Boolean变量作为正在执行方法的标记
     * A_WillRun如果等于true，表明Hello()方法即将要获得this锁，否则就保持wait
     * 因为两个方法是交替执行，同一时间只有一个执行，所以两个变量必须保持互反：A_WillRun=!B_WillRun
     * 默认将先执行的那个设为true，后执行的设为false
     */
    Boolean A_WillRun = true;
    Boolean B_WillRun = false;
    Boolean C_WillRun = false;
    /*两个方法拥有同样的锁：this*/
    public synchronized void A() {
        /* 如果Hello()不是接下来将要运行的状态，即：!A_WillRun，那么保持等待wait() 
        while用于防止线程假醒后，顺序往下执行输出功能，从而破坏交替输出*/
        while (!A_WillRun) {
            try {
                /* Hello()进行等待
                 * 调用wait()和notify()的对象必须和synchronized锁对象一致，因此这里用this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*执行Hello()核心功能*/
        System.out.println(Thread.currentThread().getName() + "A");

        /* Hello()执行完毕，设置下一步的标记状态值 */
        A_WillRun = false;
        B_WillRun = true;
        /*唤醒另一个线程*/
        this.notifyAll();

    }
    /*两个方法拥有同样的锁：this*/
    public synchronized void B() {
        while (!B_WillRun) {
            try {
                /* Word()进行等待
                 * 调用wait()和notify()的对象必须和synchronized锁对象一致，因此这里用this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*执行Word()核心功能*/
        System.out.println(Thread.currentThread().getName() + "B");
        /* Word()执行完毕，设置下一步的状态值 */
        B_WillRun = false;
        C_WillRun = true;
        /*唤醒另一个线程*/
        this.notifyAll();

    }
    /*两个方法拥有同样的锁：this*/
    public synchronized void C() {
        while (!C_WillRun) {
            try {
                /* Word()进行等待
                 * 调用wait()和notify()的对象必须和synchronized锁对象一致，因此这里用this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*执行Word()核心功能*/
        System.out.println(Thread.currentThread().getName() + "C");
        /* Word()执行完毕，设置下一步的状态值 */
        C_WillRun = false;
        A_WillRun = true;
        /*唤醒另一个线程*/
        this.notifyAll();

    }
}
