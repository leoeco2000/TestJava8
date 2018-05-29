package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class Dummy {
    /* ����ָ��Ϊvolatile����,���������޸������ܹ��� */
    public volatile int number;
}

public class AtomicIntegerFieldUpdaterTest {
    /*
     * 1�������޸���ʱ��AtomicIntegerFieldUpdater�����࣬��Ҫָ��������<T>��
     * 2��newUpdater�����������������������ֱ��Ƕ�������ͣ�Ҫ�޸ĵ����������ơ��÷��������޸�������
     */
    public final static AtomicIntegerFieldUpdater<Dummy> FIELD_INTEGER_UPDATER = AtomicIntegerFieldUpdater
            .newUpdater(Dummy.class, "number");

    public static void main(String[] args) {
        final Dummy dummy = new Dummy();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    int num = FIELD_INTEGER_UPDATER.addAndGet(dummy, 2);
                    System.out.println("�߳�" + Thread.currentThread().getName()
                            + "��ȡֵ���2�����Ϊ��" + num);
                }
            };
        }
        for (Thread t : threads) {
            t.start();
            try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("�߳�" + Thread.currentThread().getName()
                + "���ջ�ȡdummy.number���Ϊ��" + dummy.number);
    }

}