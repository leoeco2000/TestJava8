package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public final static AtomicInteger CONCURRENT_INTEGER = new AtomicInteger(1);
    public static void main(String[] args) {
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(){
                @Override
                public void run() {
                    int num=CONCURRENT_INTEGER.addAndGet(2);
                    System.out.println("线程"+Thread.currentThread().getName()+"获取值后加2，结果为："+num);
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
        System.out.println("线程"+Thread.currentThread().getName()+"最终获取结果为："+CONCURRENT_INTEGER.get());
    }

}