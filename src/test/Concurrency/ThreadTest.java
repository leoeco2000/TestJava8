package test.Concurrency;

public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int m=i;
            new Thread(){
                /*��дrun()����*/
                public void run() {
                    try {
                        /*ģ���ʱ���������߳�����2��*/
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out
                    .println("thread name is" + Thread.currentThread().getName()+"�����"+m);
                }
            }.start();
        }
    }

}