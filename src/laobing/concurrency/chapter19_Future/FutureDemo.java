package laobing.concurrency.chapter19_Future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
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

        /* 开启线程B--消费者：获取月饼 */
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("老板，给我开始做月饼...");
                    /*用缓存线程池同时开多个线程工作*/
                    ExecutorService tPool = Executors.newCachedThreadPool();
                    /*定义月饼券集合*/
                    List<Future<Integer>> futures=new ArrayList<Future<Integer>>();
                    /*启动线程A--生产者：运行耗时操作，三条生产线开始生产月饼*/
                    for(int i=0;i<3;i++){
                        Future<Integer> tFuture=tPool.submit(callable);
                        futures.add(tFuture);
                    }
                    System.out.println("等待5秒钟....");

                    /* 拿月饼 --消费结果*/
                    Boolean flag=true;
                    while(flag){
                        for(Future<Integer> ft:futures){
                            if(ft.isDone()){
                                System.out.println("用月饼券兑换到月饼，该月饼编号："+ ft.get());
                                futures.remove(ft);
                                break;//break很关键，因为遍历时改动了list
                            }
                        }
                        if(futures.size()==0){
                           flag=false;
                        }
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