package laobing.concurrency.chapter19_Future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureDemo2 {
    public static void main(String[] args) {
        /* 定义生产者:用来做月饼的Callable */
        final Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                /* 模拟耗时操作，需要5秒 */
                Thread.sleep(5000);
                /* 返回一盒做好的月饼编号 */
                return new Random().nextInt(10000);
            }
        };

        /* 开启线程B--消费者：获取月饼 */
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("老板，给我开始做月饼...");
                    /*用缓存线程池可同时开多个线程工作*/
                    ExecutorService tPool = Executors.newCachedThreadPool();
                    /*定义领多盒的月饼券*/
                    CompletionService<Integer> CookCompletion = new ExecutorCompletionService<Integer>(tPool);
                    /*启动线程A--生产者：运行耗时操作，三条生产线开始生产月饼*/
                    for(int i=0;i<3;i++){
                        CookCompletion.submit(callable);
                    }
                    System.out.println("等待5秒钟....");
                    /* 拿到月饼 */
                    for(int i=0;i<3;i++){
                        System.out.println("用月饼券兑换到月饼，该月饼编号："+ CookCompletion.take().get());
                    }
                    System.out.println("拿饼回家...");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}