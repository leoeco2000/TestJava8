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
        CountDownLatch cDownLatch=new CountDownLatch(3);//��ʼ������ֵΪ3����Ӧ3��ά����

        /*1��������վ*/
        System.out.println("F1������վ��ʱ�䣺"+Calendar.getInstance().get(Calendar.SECOND));

        /*2������ά����ͬʱ��ʼ������ά��*/
        List<mechanician> mechanician_team=new ArrayList<mechanician>();
        mechanician_team.add(new mechanician("��עȼ��", cDownLatch));
        mechanician_team.add(new mechanician("������̥", cDownLatch));
        mechanician_team.add(new mechanician("����ɲ��Ƭ", cDownLatch));
        for(mechanician mec:mechanician_team){
            new Thread(mec).start();
        }

        /*3���ȴ���ʦ����������ʵ�ʾ��ǵȴ�cDownLatch���������0*/
        cDownLatch.await();

        /*4�����ά��������*/
        System.out.println("F1����ά����ϣ�������ʱ�䣺"+Calendar.getInstance().get(Calendar.SECOND));
    }

    /*ά�޼�ʦ��*/
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
                System.out.println(Thread.currentThread().getName()+"--- "+work+" ��ɣ������ʱ��"+random+"��");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                /*��ǰ��ʦ���������,cDownLatch��������1
                 *ͨ��countDown����finally����ʹ��*/
                cDownLatch.countDown();
            }
        }
    }
}