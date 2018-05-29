package laobing.concurrency.chapter22_Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        final Semaphore permit=new Semaphore(3,true);
        for(int i=0;i<4;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        /*�����߳����Ի�ȡ���֤*/
                        permit.acquire();
                        System.out.println(Thread.currentThread().getName()
                                    + "��ȡ��ɳɹ����������ʣ�ࣺ" + permit.availablePermits());
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally{
                        System.out.println(Thread.currentThread().getName()
                                + "�ͷ���ɳɹ����������ʣ�ࣺ" + (permit.availablePermits()+1));
                        /*�����߳������ͷ����֤*/
                        permit.release();

                    }

                }
            }).start();
        }
    }
}