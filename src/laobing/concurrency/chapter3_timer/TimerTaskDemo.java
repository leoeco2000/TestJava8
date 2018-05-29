package laobing.concurrency.chapter3_timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerTaskDemo {
    public static void main(String[] args) {
        Runnable runable=new Runnable() {
            public void run() {
                System.out.println("���߳�ִ�����񣬵�ǰʱ�䣺"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        };
        try {
            System.out.println("���߳��������߳�ʱ�䣺"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            scheduleThread(5L,3,runable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /***
     * 
     * @param duration ָ��ʲôʱ������� ��λ����
     * @param timeInterval ÿ�����м��ʱ�� ��λ����
     * @param runnable �����е�Runable����
     * @throws InterruptedException
     */
    static void scheduleThread(Long duration,Integer timeInterval,Runnable runnable) throws InterruptedException{
        /*�����ȴ�*/
        TimeUnit.SECONDS.sleep(duration);
        //Thread.sleep(duration*1000);
        final Runnable interiorRun=runnable;
        final Integer interiorTimeInterval=timeInterval;
        /*����*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    /*ִ�з���*/
                    interiorRun.run();
                    try {
                        /*����ִ�м��*/
                        TimeUnit.SECONDS.sleep(interiorTimeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}