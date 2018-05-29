package test.Concurrency;

public class NewThreadDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /* 模拟耗时操作，让线程休眠2秒 */
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Executive agent:Runable 's run()");
            }
        }) {
            public void run() {
                try {
                    /* 模拟耗时操作，让线程休眠2秒 */
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out
                        .println("Executive agent:Thread 's Override run()");

            }
        }.start();
    }

}