package laobing.concurrency.chapter1_thread;

public class extendThread {
    public static void main(String[] args) {
        /*在主线程中开启10个线程，每个线程需要耗时2秒。但因为线程并发，所以总体运行时间也在2秒左右，并非20秒。*/
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
        /*覆写run()方法*/
        public void run() {
            try {
                /*模拟耗时操作，让线程休眠2秒*/
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out
            .println("Class name is : "+this.put+",thread name is ：" + Thread.currentThread().getName());
        }
    }

}