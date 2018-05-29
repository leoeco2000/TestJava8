package test.Thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main {  
	  
    public static void main(String[] args) {  
        //����2�����CountDownLatch����  
        CountDownLatch countDownLatch = new CountDownLatch(2);  
          
        //��countDownLatch��������ô��ݸ����߳���  
        WorkThread workThread1 = new WorkThread(countDownLatch);  
        WorkThread workThread2 = new WorkThread(countDownLatch);  
        workThread1.start();  
        workThread2.start();  
  
        try {  
            //����await����������ǰ�̣߳��ȴ����߳���ɺ��ڼ���ִ��  
            countDownLatch.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
        System.out.println("run next process.");  
    }  
}