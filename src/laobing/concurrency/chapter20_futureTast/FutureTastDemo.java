package laobing.concurrency.chapter20_futureTast;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTastDemo {
    public static void main(String[] args) {
        /* 定义生产者:用来做月饼的Callable */
        final Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                /*模拟耗时操作，需要5秒*/
                Thread.sleep(5000);
                /*返回一盒做好的月饼编号*/
                return new Random().nextInt(10000);
            }
        };

        /*开启线程B--消费者：获取月饼*/
        Runnable runnable=new Runnable() {
            public void run() {
                try {
                     ExecutorService tPool = Executors.newSingleThreadExecutor();
                     System.out.println("老板，给我开始做月饼...");
                     /*启动线程A--生产者：运行耗时操作，生产月饼
                      *同时获得一张月饼券CookTicket*/
                     //final Future<Integer> CookTicket = tPool.submit(callable);

                     FutureTask<Integer> CookTicket = new FutureTask<Integer>(callable);
                     tPool.submit(CookTicket);
                     //CookTicket.run();另一种调用方式
                     //new Thread(CookTicket).run();另一种调用方式

                     /*拿到月饼*/
                     System.out.println("5秒钟后用月饼券兑换到月饼，该盒月饼编号："+CookTicket.get());
                     System.out.println("拿饼回家...");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}