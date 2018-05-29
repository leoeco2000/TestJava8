package laobing.concurrency.chapter9_volatile;

import java.util.Calendar;

public class volatileDemo {
    private static volatile boolean stopFlag;
    public static void main(String[] args) throws InterruptedException {
        Thread volatileThread =new Thread(){
            @Override
            public void run() {
                while(!stopFlag){
                    System.out.print(Calendar.getInstance().get(Calendar.SECOND)+",");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        volatileThread.start();
        Thread.sleep(3000);
        stopFlag=true;
    }
}