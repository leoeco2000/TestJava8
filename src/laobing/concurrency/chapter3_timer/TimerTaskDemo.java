package laobing.concurrency.chapter3_timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerTaskDemo {
    public static void main(String[] args) {
        Runnable runable=new Runnable() {
            public void run() {
                System.out.println("子线程执行任务，当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        };
        try {
            System.out.println("主线程启动子线程时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            scheduleThread(5L,3,runable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /***
     * 
     * @param duration 指定什么时间后运行 单位：秒
     * @param timeInterval 每次运行间隔时间 单位：秒
     * @param runnable 待运行的Runable对象
     * @throws InterruptedException
     */
    static void scheduleThread(Long duration,Integer timeInterval,Runnable runnable) throws InterruptedException{
        /*阻塞等待*/
        TimeUnit.SECONDS.sleep(duration);
        //Thread.sleep(duration*1000);
        final Runnable interiorRun=runnable;
        final Integer interiorTimeInterval=timeInterval;
        /*运行*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    /*执行方法*/
                    interiorRun.run();
                    try {
                        /*任务执行间隔*/
                        TimeUnit.SECONDS.sleep(interiorTimeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}