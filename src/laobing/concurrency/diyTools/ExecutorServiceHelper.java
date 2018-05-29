package laobing.concurrency.diyTools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceHelper {
  /**
   *  ��ȡ��Ծ�� cpu����
   */
  private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
  private final static BlockingQueue<Runnable> mWorkQueue;
  private final static long KEEP_ALIVE_TIME = 3L;
  private final static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
  private static ThreadFactory mThreadFactory;

  static {
      mWorkQueue = new LinkedBlockingQueue<Runnable>();
//Ĭ�ϵĹ����������´������߳�����Ϊ��pool-[��������̳߳ر��]-thread-[�̱߳��]
      //mThreadFactory= Executors.defaultThreadFactory();
      mThreadFactory = new NamedThreadFactory();
//      System.out.println("NUMBER_OF_CORES:"+NUMBER_OF_CORES);
  }

  public  static  void  execute(Runnable runnable){
      if (runnable==null){
          return;
      }
      /**
       * 1.���̳߳�С�� corePoolSize ʱ�����ύ���񽫴���һ�����߳�ִ�����񣬼�ʹ��ʱ�̳߳��д��ڿ����̡߳�
       * 2.���̳߳شﵽ corePoolSize ʱ�����ύ���񽫱����� workQueue �У��ȴ��̳߳����������ִ��
       * 3.�� workQueue �������� maximumPoolSize > corePoolSizeʱ�����ύ����ᴴ�����߳�ִ������
       * 4.���ύ���������� maximumPoolSize ʱ�����ύ������ RejectedExecutionHandler ����
       * 5.���̳߳��г��� corePoolSize �̣߳�����ʱ��ﵽ keepAliveTime ʱ���رտ����߳�
       * 6.������ allowCoreThreadTimeOut(true) ʱ���̳߳��� corePoolSize �߳̿���ʱ��ﵽ keepAliveTime Ҳ���ر�
       **/
       /**
 maximumPoolSize �Ƽ�ȡֵ
  ����� CPU �ܼ������񣬾���Ҫ����ѹեCPU���ο�ֵ������Ϊ NUMBER_OF_CORES + 1 �� NUMBER_OF_CORES + 2
  ����� IO �ܼ������񣬲ο�ֵ��������Ϊ NUMBER_OF_CORES * 2
       */
      ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
              NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
              mWorkQueue,mThreadFactory);
      executorService.execute(runnable);
  }
}
