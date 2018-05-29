package laobing.concurrency.chapter5_synchronized;

/*��������ⲿ��*/
class Pan1 {
	  /*����һ����,���������������ͣ��磺Object,Int,String�ȣ�ֵҲ�������⣬����Ҫ��֤���з��ʸ�ֵ���̵߳õ�ͬ���Ľ��*/
    private String lock = "anything"; 
    /*��⿷������÷����������*/
    public void Cook(String[] steps) {
        /*��synchronized���������ִ�в���*/
        synchronized(lock){
            for (int i = 0; i < steps.length; i++) {
                /*ģ�⾺����ɵ��̵߳ȴ�������Ч������Щ*/
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(steps[i]);
            }
            System.out.println("");
        }
    }
    /*�ཷ�����������裺a1.���⣬a2.���Σ�a3.������  a4 a5....*/
    String[] steps_LaJiaoChaoRou={"a1.","a2.","a3.","a4.","a5.","a6.","a7.","a8.","a9.","a10.","a11.","a12.","a13.","a14."
,"a15.","a16.","a17.","a18.","OK����������"};
    /*���ѳ����������裺b1.�ŵ���b2.���Σ�b3.�ŷ���......*/
    String[] steps_FanQieChaoDan={"b1.","b2.","b3.","b4.","b5.","b6.","OK:���ѳ���"};
}

public class chapter2 {
    public static void main(String[] args) {
        final Pan1 pan=new Pan1();

        /*�߳�1���ϴ��ཷ���⡣*/
        new Thread(){
            public void run() {
                /*Ϊ�˿�������Ч������������ѭ����һ��ʱ����ֹ����ֹͣ���а�ť*/
                while (true) {
                    try {
                        /*ģ���ཷ������Ҫ5��;*/
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_LaJiaoChaoRou);
                }
            }
        }.start();

        /*�߳�2���϶������ѳ�����*/
        new Thread(){
            public void run() {
                /*Ϊ�˿�������Ч������������ѭ����һ��ʱ����ֹ����ֹͣ���а�ť*/
                while (true) {
                    try {
                        /*ģ�ⷬ�ѳ�����Ҫ2��;*/
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_FanQieChaoDan);
                }
            }
        }.start();
    }
}