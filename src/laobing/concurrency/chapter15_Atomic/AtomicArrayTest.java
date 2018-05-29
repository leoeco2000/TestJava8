package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {
	public final static AtomicIntegerArray CONCURRENT_INTEGER_ARRAY = new AtomicIntegerArray(new int[]{35,99});
    public static void main(String[] args) {
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(){
                @Override
                public void run() {
                    int result0=CONCURRENT_INTEGER_ARRAY.addAndGet(0,5);
                    int result1=CONCURRENT_INTEGER_ARRAY.addAndGet(1,7);
                    System.out.println("线程"+Thread.currentThread().getName()+"为数组元素[0]加5，结果为："+result0+";为数组元素[1]加7，结果为："+result1);
                }
            };
        }
        for(Thread t:threads ){
            t.start();
        }
        for(Thread t:threads ){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("======查看数组结果======");
        for(int i = 0 ; i < CONCURRENT_INTEGER_ARRAY.length() ; i++) {
            System.out.println("数组元素["+i+"]值为:"+CONCURRENT_INTEGER_ARRAY.get(i));
        }

    }

}