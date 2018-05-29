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
                        /*阻塞线程用以获取许可证*/
                        permit.acquire();
                        System.out.println(Thread.currentThread().getName()
                                    + "获取许可成功。可用许可剩余：" + permit.availablePermits());
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally{
                        System.out.println(Thread.currentThread().getName()
                                + "释放许可成功。可用许可剩余：" + (permit.availablePermits()+1));
                        /*阻塞线程用以释放许可证*/
                        permit.release();

                    }

                }
            }).start();
        }
    }
}