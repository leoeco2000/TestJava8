package test.Concurrency;

public class NewThreadDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /* ģ���ʱ���������߳�����2�� */
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Executive agent:Runable 's run()");
            }
        }) {
            public void run() {
                try {
                    /* ģ���ʱ���������߳�����2�� */
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