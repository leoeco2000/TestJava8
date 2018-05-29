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
    /* ����������:�������±���Callable */
    final Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        /* ģ���ʱ��������Ҫ5�� */
        Thread.sleep(5000);
        /* ����һ�����õ��±���� */
        return new Random().nextInt(10000);
      }
    };
    FutureTask<Integer> CookTicket = new FutureTask<Integer>(callable) {
      /* ��ĳ���߳������������ϻص�done������ִ���������� */
      @Override
      protected void done() {
        super.done();
        try {
          /* get()����ȡ����ķ��� */
          System.out.println("5���Ӻ����±�ȯ�һ����±����ú��±���ţ�" + get());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }

    };
    /* �����߳�B--�����ߣ���ȡ�±� */
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("�ϰ壬���ҿ�ʼ���±�...");
        /* �û����̳߳�ͬʱ������̹߳��� */
//        ExecutorService tPool = Executors.newCachedThreadPool();
        
        ExecutorService tPool = new ThreadPoolExecutor(
            5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new NamedThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

//        tPool.execute(()-> System.out.println(Thread.currentThread().getName()));
//        tPool.shutdown();//gracefully shutdown   

        /* �����߳�A--�����ߣ����к�ʱ���������������߿�ʼ�����±� */
        for (int i = 0; i < 3; i++) {
          tPool.submit(CookTicket);
          // new Thread(CookTicket).run();
        }
      }
    };
    new Thread(runnable).start();
  }
}
