package test.Thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class WorkThread extends Thread {  
    private CountDownLatch countDownLatch;  
  
    public WorkThread(CountDownLatch countDownLatch) {  
        this.countDownLatch = countDownLatch;  
    }  
  
    @Override  
    public void run() {  
        try {  
            System.out.println(getName() + "run start.");  
            sleep(1000);  
            //ִ�����������֮��countDown����һ����  
            countDownLatch.countDown();  
            System.out.println(getName() + "run finished.");  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}