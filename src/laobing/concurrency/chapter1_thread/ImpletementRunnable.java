package laobing.concurrency.chapter1_thread;

public class ImpletementRunnable {
    public static void main(String[] args) {
        /* 在主线程中开启10个线程，每个线程需要耗时2秒，但因为线程并发，所以总体运行时间也在2秒左右，并非20秒。 */
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        /* 模拟耗时操作，让线程休眠2秒 */
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread name is"
                            + Thread.currentThread().getName());

                }
            }).start();
        }
        
        
    }
}
