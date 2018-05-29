package laobing.concurrency.chapter25_Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
    public static void main(String[] args) {
        final Exchanger<Integer> exgr = new Exchanger<Integer>();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        /*线程1用于计算结果*/
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                int result=50*70;
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"计算50*70的结果是:"+result);

                    /*同步点。先到达的线程会在这里等侯*/
                    exgr.exchange(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*线程2用于计算并检验*/
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                int result=0;
                for(int i=0;i<50;i++){
                    result+=70;
                }
                System.out.println("线程"+Thread.currentThread().getName()+"计算50*70的结果是:"+result);
                try {
                    /*同步点。先到达的线程会在这里等侯*/
                    int result_FromOtherThread=exgr.exchange(result);
                    System.out.println("线程"+Thread.currentThread().getName()+"从另一个线程获取得值是："+result_FromOtherThread);
                    if(result_FromOtherThread==result){
                        System.out.println("两边计算结果一样，验证成功");
                    }else{
                        System.out.println("两边计算结果不一样，验证失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}