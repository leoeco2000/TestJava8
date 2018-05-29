package laobing.concurrency.chapter6_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pan {
    private Lock lock = new ReentrantLock(); 
    /*��⿷������÷����������*/
    public void Cook(String[] steps) {
         lock.lock();
         try{
            for (int i = 0; i < steps.length; i++) {
                /*ģ�⾺����ɵ��̵߳ȴ�������Ч������Щ*/
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(steps[i]);
            }
            System.out.println("");
         }finally{
             /*
             * synchronized����JVM������ʵ�ֵģ���������ͨ��һЩ��ع��߼��synchronized��������
             * �����ڴ���ִ�г����쳣ʱ��JVM���Զ��ͷ�������
             * ����ʹ��Lock���У�lock��ͨ������ʵ�ֵġ�
             * Ҫ��֤����һ���ᱻ�ͷţ��ͱ��뽫unLock()�ŵ�finally{}��
             */
            lock.unlock();
         }

    }
    /*�ཷ�����������裺a1.���⣬a2.���Σ�a3.������  a4 a5....*/
    String[] steps_LaJiaoChaoRou={"a1.","a2.","a3.","a4.","a5.","a6.","a7.","a8.","a9.","a10.","OK����������"};
    /*���ѳ����������裺b1.�ŵ���b2.���Σ�b3.�ŷ���*/
    String[] steps_FanQieChaoDan={"b1.","b2.","b3.","b4.","b5.","b6.","OK:���ѳ���"};
}

public class LockDemo {
    public static void main(String[] args) {

        final Pan pan=new Pan();
        /*�߳�1���ϴ��ཷ���⡣*/
        new Thread(){
            public void run() {
                /*Ϊ�˿�������Ч������������ѭ����һ��ʱ����ֹ����ֹͣ���а�ť*/
                while (true) {
                    try {
                        /*�ཷ������Ҫ5��;*/
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_LaJiaoChaoRou);
                }
            }
        }.start();

        /*�߳�2���϶������ѳ�����*/
        new Thread(){
            public void run() {
                /*Ϊ�˿�������Ч������������ѭ����һ��ʱ����ֹ����ֹͣ���а�ť*/
                while (true) {
                    try {
                        /*���ѳ�����Ҫ5��;*/
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_FanQieChaoDan);
                }
            }
        }.start();
    }
}