package laobing.concurrency.chapter3_timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerTaskDemo2 {
    public static void main(String[] args) {
        try {
            System.out.println("���߳��������߳�ʱ�䣺"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            /*�����ȴ�*/
            TimeUnit.SECONDS.sleep(5L);
            /*����*/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        /*ִ�з���*/
                    	System.out.println("���߳�ִ�����񣬵�ǰʱ�䣺"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        try {
                            /*����ִ�м��*/
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