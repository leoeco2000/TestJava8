package laobing.concurrency.chapter3_timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerTaskDemo2 {
    public static void main(String[] args) {
        try {
            System.out.println("主线程启动子线程时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            /*阻塞等待*/
            TimeUnit.SECONDS.sleep(5L);
            /*运行*/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        /*执行方法*/
                    	System.out.println("子线程执行任务，当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        try {
                            /*任务执行间隔*/
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}