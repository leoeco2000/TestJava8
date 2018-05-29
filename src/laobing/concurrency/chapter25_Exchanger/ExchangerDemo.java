package laobing.concurrency.chapter25_Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
    public static void main(String[] args) {
        final Exchanger<Integer> exgr = new Exchanger<Integer>();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        /*�߳�1���ڼ�����*/
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                int result=50*70;
                try {
                    System.out.println("�߳�"+Thread.currentThread().getName()+"����50*70�Ľ����:"+result);

                    /*ͬ���㡣�ȵ�����̻߳�������Ⱥ�*/
                    exgr.exchange(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*�߳�2���ڼ��㲢����*/
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                int result=0;
                for(int i=0;i<50;i++){
                    result+=70;
                }
                System.out.println("�߳�"+Thread.currentThread().getName()+"����50*70�Ľ����:"+result);
                try {
                    /*ͬ���㡣�ȵ�����̻߳�������Ⱥ�*/
                    int result_FromOtherThread=exgr.exchange(result);
                    System.out.println("�߳�"+Thread.currentThread().getName()+"����һ���̻߳�ȡ��ֵ�ǣ�"+result_FromOtherThread);
                    if(result_FromOtherThread==result){
                        System.out.println("���߼�����һ������֤�ɹ�");
                    }else{
                        System.out.println("���߼�������һ������֤ʧ��");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}