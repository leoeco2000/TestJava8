package laobing.concurrency.chapter20_futureTast;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import laobing.concurrency.diyTools.NamedThreadFactory;

public class FutureTastDemo2 {
  public static void main(String[] args) {
    /* 定义生产者:用来做月饼的Callable */
    final Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        /* 模拟耗时操作，需要5秒 */
        Thread.sleep(5000);
        /* 返回一盒做好的月饼编号 */
        return new Random().nextInt(10000);
      }
    };
    FutureTask<Integer> CookTicket = new FutureTask<Integer>(callable) {
      /* 当某个线程完成任务后，马上回调done函数，执行消费任务。 */
      @Override
      protected void done() {
        super.done();
        try {
          /* get()是提取结果的方法 */
          System.out.println("5秒钟后用月饼券兑换到月饼，该盒月饼编号：" + get());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }

    };
    /* 开启线程B--消费者：获取月饼 */
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("老板，给我开始做月饼...");
        /* 用缓存线程池同时开多个线程工作 */
//        ExecutorService tPool = Executors.newCachedThreadPool();
        
        ExecutorService tPool = new ThreadPoolExecutor(
            5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new NamedThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

//        tPool.execute(()-> System.out.println(Thread.currentThread().getName()));
//        tPool.shutdown();//gracefully shutdown   

        /* 启动线程A--生产者：运行耗时操作，三条生产线开始生产月饼 */
        for (int i = 0; i < 3; i++) {
          tPool.submit(CookTicket);
          // new Thread(CookTicket).run();
        }
      }
    };
    new Thread(runnable).start();
  }
}
