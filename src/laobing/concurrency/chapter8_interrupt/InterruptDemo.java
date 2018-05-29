package laobing.concurrency.chapter8_interrupt;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new TestRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        /*三秒后中断线程th1*/
        Thread.sleep(3000);
        th1.interrupt();
    }
}
class TestRunnable implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is running...");

            long time = System.currentTimeMillis();// 获取系统时间的毫秒数
//            while((System.currentTimeMillis() - time < 1000)){
//                /*程序循环运行1秒钟，不同于sleep(1000)会阻塞进程。*/
//            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
                //中断时如何处理由被中断的线程自己决定
                return;
            }
        }
    }
}