package laobing.concurrency.diyTools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceHelper {
  /**
   *  获取活跃的 cpu数量
   */
  private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
  private final static BlockingQueue<Runnable> mWorkQueue;
  private final static long KEEP_ALIVE_TIME = 3L;
  private final static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
  private static ThreadFactory mThreadFactory;

  static {
      mWorkQueue = new LinkedBlockingQueue<Runnable>();
//默认的工厂方法将新创建的线程命名为：pool-[虚拟机中线程池编号]-thread-[线程编号]
      //mThreadFactory= Executors.defaultThreadFactory();
      mThreadFactory = new NamedThreadFactory();
//      System.out.println("NUMBER_OF_CORES:"+NUMBER_OF_CORES);
  }

  public  static  void  execute(Runnable runnable){
      if (runnable==null){
          return;
      }
      /**
       * 1.当线程池小于 corePoolSize 时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
       * 2.当线程池达到 corePoolSize 时，新提交任务将被放入 workQueue 中，等待线程池中任务调度执行
       * 3.当 workQueue 已满，且 maximumPoolSize > corePoolSize时，新提交任务会创建新线程执行任务
       * 4.当提交任务数超过 maximumPoolSize 时，新提交任务由 RejectedExecutionHandler 处理
       * 5.当线程池中超过 corePoolSize 线程，空闲时间达到 keepAliveTime 时，关闭空闲线程
       * 6.当设置 allowCoreThreadTimeOut(true) 时，线程池中 corePoolSize 线程空闲时间达到 keepAliveTime 也将关闭
       **/
       /**
 maximumPoolSize 推荐取值
  如果是 CPU 密集型任务，就需要尽量压榨CPU，参考值可以设为 NUMBER_OF_CORES + 1 或 NUMBER_OF_CORES + 2
  如果是 IO 密集型任务，参考值可以设置为 NUMBER_OF_CORES * 2
       */
      ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
              NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
              mWorkQueue,mThreadFactory);
      executorService.execute(runnable);
  }
}
