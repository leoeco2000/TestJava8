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
        /* ����������:�������±���Callable */
        final Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                /* ģ���ʱ��������Ҫ5�� */
                Thread.sleep(5000);
                /* ����һ�����õ��±���� */
                return new Random().nextInt(10000);
            }
        };

        /* �����߳�B--�����ߣ���ȡ�±� */
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("�ϰ壬���ҿ�ʼ���±�...");
                    /*�û����̳߳ؿ�ͬʱ������̹߳���*/
                    ExecutorService tPool = Executors.newCachedThreadPool();
                    /*�������е��±�ȯ*/
                    CompletionService<Integer> CookCompletion = new ExecutorCompletionService<Integer>(tPool);
                    /*�����߳�A--�����ߣ����к�ʱ���������������߿�ʼ�����±�*/
                    for(int i=0;i<3;i++){
                        CookCompletion.submit(callable);
                    }
                    System.out.println("�ȴ�5����....");
                    /* �õ��±� */
                    for(int i=0;i<3;i++){
                        System.out.println("���±�ȯ�һ����±������±���ţ�"+ CookCompletion.take().get());
                    }
                    System.out.println("�ñ��ؼ�...");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}