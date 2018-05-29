package laobing.concurrency.chapter1_thread;

public class ImpletementRunnable {
    public static void main(String[] args) {
        /* �����߳��п���10���̣߳�ÿ���߳���Ҫ��ʱ2�룬����Ϊ�̲߳�����������������ʱ��Ҳ��2�����ң�����20�롣 */
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        /* ģ���ʱ���������߳�����2�� */
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
