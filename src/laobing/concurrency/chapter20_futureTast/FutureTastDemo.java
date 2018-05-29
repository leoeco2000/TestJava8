package laobing.concurrency.chapter20_futureTast;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTastDemo {
    public static void main(String[] args) {
        /* ����������:�������±���Callable */
        final Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                /*ģ���ʱ��������Ҫ5��*/
                Thread.sleep(5000);
                /*����һ�����õ��±����*/
                return new Random().nextInt(10000);
            }
        };

        /*�����߳�B--�����ߣ���ȡ�±�*/
        Runnable runnable=new Runnable() {
            public void run() {
                try {
                     ExecutorService tPool = Executors.newSingleThreadExecutor();
                     System.out.println("�ϰ壬���ҿ�ʼ���±�...");
                     /*�����߳�A--�����ߣ����к�ʱ�����������±�
                      *ͬʱ���һ���±�ȯCookTicket*/
                     //final Future<Integer> CookTicket = tPool.submit(callable);

                     FutureTask<Integer> CookTicket = new FutureTask<Integer>(callable);
                     tPool.submit(CookTicket);
                     //CookTicket.run();��һ�ֵ��÷�ʽ
                     //new Thread(CookTicket).run();��һ�ֵ��÷�ʽ

                     /*�õ��±�*/
                     System.out.println("5���Ӻ����±�ȯ�һ����±����ú��±���ţ�"+CookTicket.get());
                     System.out.println("�ñ��ؼ�...");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}