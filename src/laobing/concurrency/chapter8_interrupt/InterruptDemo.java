package laobing.concurrency.chapter8_interrupt;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new TestRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        /*������ж��߳�th1*/
        Thread.sleep(3000);
        th1.interrupt();
    }
}
class TestRunnable implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is running...");

            long time = System.currentTimeMillis();// ��ȡϵͳʱ��ĺ�����
//            while((System.currentTimeMillis() - time < 1000)){
//                /*����ѭ������1���ӣ���ͬ��sleep(1000)���������̡�*/
//            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
                //�ж�ʱ��δ����ɱ��жϵ��߳��Լ�����
                return;
            }
        }
    }
}