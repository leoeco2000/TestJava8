package laobing.concurrency.chapter1_thread;

public class extendThread {
    public static void main(String[] args) {
        /*�����߳��п���10���̣߳�ÿ���߳���Ҫ��ʱ2�롣����Ϊ�̲߳�����������������ʱ��Ҳ��2�����ң�����20�롣*/
        for (int i = 0; i < 10; i++) {
            demothread abc= new demothread("chapter1_thread"+String.valueOf(i));
            abc.start();
        }
    }
    static class demothread extends Thread {
        String put;
        public demothread(String name) {
            super(name);
            this.put = name;
        }
        /*��дrun()����*/
        public void run() {
            try {
                /*ģ���ʱ���������߳�����2��*/
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out
            .println("Class name is : "+this.put+",thread name is ��" + Thread.currentThread().getName());
        }
    }

}