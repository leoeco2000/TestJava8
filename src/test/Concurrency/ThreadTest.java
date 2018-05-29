package test.Concurrency;

public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int m=i;
            new Thread(){
                /*覆写run()方法*/
                public void run() {
                    try {
                        /*模拟耗时操作，让线程休眠2秒*/
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out
                    .println("thread name is" + Thread.currentThread().getName()+"输出："+m);
                }
            }.start();
        }
    }

}