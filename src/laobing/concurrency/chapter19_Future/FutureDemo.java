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
        /* ����������:�������±���Callable */
        final Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                /*ģ���ʱ��������Ҫ5��*/
                Thread.sleep(5000);
                /*����һ�����õ��±����*/
                return new Random().nextInt(10000);
            }
        };

        /* �����߳�B--�����ߣ���ȡ�±� */
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("�ϰ壬���ҿ�ʼ���±�...");
                    /*�û����̳߳�ͬʱ������̹߳���*/
                    ExecutorService tPool = Executors.newCachedThreadPool();
                    /*�����±�ȯ����*/
                    List<Future<Integer>> futures=new ArrayList<Future<Integer>>();
                    /*�����߳�A--�����ߣ����к�ʱ���������������߿�ʼ�����±�*/
                    for(int i=0;i<3;i++){
                        Future<Integer> tFuture=tPool.submit(callable);
                        futures.add(tFuture);
                    }
                    System.out.println("�ȴ�5����....");

                    /* ���±� --���ѽ��*/
                    Boolean flag=true;
                    while(flag){
                        for(Future<Integer> ft:futures){
                            if(ft.isDone()){
                                System.out.println("���±�ȯ�һ����±������±���ţ�"+ ft.get());
                                futures.remove(ft);
                                break;//break�ܹؼ�����Ϊ����ʱ�Ķ���list
                            }
                        }
                        if(futures.size()==0){
                           flag=false;
                        }
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