package laobing.concurrency.chapter3_timer;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses=Executors.newScheduledThreadPool(2);
        ses.scheduleAtFixedRate(new MyTimerTask(), 2, 1, TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(new MyTimerTask(), 1, 1, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(10);
            /*10���ֹͣ����*/
            ses.shutdown();//ֹͣ�����̳߳��ϵ�����runable��
            System.out.print("--����10��ֹͣ--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*TimerTaskʵ��Runable�ӿ�*/
    static class MyTimerTask extends java.util.TimerTask {
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+ Calendar.getInstance().get(Calendar.SECOND));
        }
    }
}
