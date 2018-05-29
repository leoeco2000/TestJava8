package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class Dummy {
    /* 必须指定为volatile类型,否则属性修改器不能工作 */
    public volatile int number;
}

public class AtomicIntegerFieldUpdaterTest {
    /*
     * 1、定义修改器时用AtomicIntegerFieldUpdater泛型类，需要指明类类型<T>。
     * 2、newUpdater工厂方法接收两个参数，分别是对象的类型，要修改的类属性名称。该方法返回修改器对象。
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
                    System.out.println("线程" + Thread.currentThread().getName()
                            + "获取值后加2，结果为：" + num);
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
        System.out.println("线程" + Thread.currentThread().getName()
                + "最终获取dummy.number结果为：" + dummy.number);
    }

}