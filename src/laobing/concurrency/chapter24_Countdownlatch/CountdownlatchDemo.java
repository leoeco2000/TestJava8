package laobing.concurrency.chapter24_Countdownlatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountdownlatchDemo {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cDownLatch=new CountDownLatch(3);//初始计数器值为3，对应3个维修组

        /*1、赛车进站*/
        System.out.println("F1赛车进站，时间："+Calendar.getInstance().get(Calendar.SECOND));

        /*2、三个维修组同时开始对赛车维护*/
        List<mechanician> mechanician_team=new ArrayList<mechanician>();
        mechanician_team.add(new mechanician("加注燃油", cDownLatch));
        mechanician_team.add(new mechanician("更换轮胎", cDownLatch));
        mechanician_team.add(new mechanician("更换刹车片", cDownLatch));
        for(mechanician mec:mechanician_team){
            new Thread(mec).start();
        }

        /*3、等待技师完成三项工作。实际就是等待cDownLatch计数器变成0*/
        cDownLatch.await();

        /*4、完成维护，出发*/
        System.out.println("F1赛车维修完毕，出发！时间："+Calendar.getInstance().get(Calendar.SECOND));
    }

    /*维修技师类*/
    static class mechanician implements Runnable{
        String work;
        CountDownLatch cDownLatch;
        public mechanician(String work,CountDownLatch cDownLatch) {
            this.work = work;
            this.cDownLatch = cDownLatch;
        }
        @Override
        public void run() {
            try {
                int random=new Random().nextInt(7);
                TimeUnit.SECONDS.sleep(random);
                System.out.println(Thread.currentThread().getName()+"--- "+work+" 完成，此组耗时："+random+"秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                /*当前技师的任务完成,cDownLatch计算器减1
                 *通常countDown放在finally里中使用*/
                cDownLatch.countDown();
            }
        }
    }
}