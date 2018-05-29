package laobing.concurrency.chapter11_communication;

public class ABCTest {
    public static void main(String[] args) {
        final Communication LockTest = new Communication();
        /* ��һ���߳�˵5��Hello */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.A();
                }
            }
        }).start();
        /* �ڶ����߳�˵5��Word */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.B();
                }
            }
        }).start();
        /* �ڶ����߳�˵5��Word */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    LockTest.C();
                }
            }
        }).start();
    }
}
/* �����ⲿ������SyncLockTest */
class Communication {
    /*
     * Ϊ�����Ӵ���ɶ��ԣ�������������Boolean������Ϊ����ִ�з����ı��
     * A_WillRun�������true������Hello()��������Ҫ���this��������ͱ���wait
     * ��Ϊ���������ǽ���ִ�У�ͬһʱ��ֻ��һ��ִ�У����������������뱣�ֻ�����A_WillRun=!B_WillRun
     * Ĭ�Ͻ���ִ�е��Ǹ���Ϊtrue����ִ�е���Ϊfalse
     */
    Boolean A_WillRun = true;
    Boolean B_WillRun = false;
    Boolean C_WillRun = false;
    /*��������ӵ��ͬ��������this*/
    public synchronized void A() {
        /* ���Hello()���ǽ�������Ҫ���е�״̬������!A_WillRun����ô���ֵȴ�wait() 
        while���ڷ�ֹ�̼߳��Ѻ�˳������ִ��������ܣ��Ӷ��ƻ��������*/
        while (!A_WillRun) {
            try {
                /* Hello()���еȴ�
                 * ����wait()��notify()�Ķ�������synchronized������һ�£����������this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*ִ��Hello()���Ĺ���*/
        System.out.println(Thread.currentThread().getName() + "A");

        /* Hello()ִ����ϣ�������һ���ı��״ֵ̬ */
        A_WillRun = false;
        B_WillRun = true;
        /*������һ���߳�*/
        this.notifyAll();

    }
    /*��������ӵ��ͬ��������this*/
    public synchronized void B() {
        while (!B_WillRun) {
            try {
                /* Word()���еȴ�
                 * ����wait()��notify()�Ķ�������synchronized������һ�£����������this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*ִ��Word()���Ĺ���*/
        System.out.println(Thread.currentThread().getName() + "B");
        /* Word()ִ����ϣ�������һ����״ֵ̬ */
        B_WillRun = false;
        C_WillRun = true;
        /*������һ���߳�*/
        this.notifyAll();

    }
    /*��������ӵ��ͬ��������this*/
    public synchronized void C() {
        while (!C_WillRun) {
            try {
                /* Word()���еȴ�
                 * ����wait()��notify()�Ķ�������synchronized������һ�£����������this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*ִ��Word()���Ĺ���*/
        System.out.println(Thread.currentThread().getName() + "C");
        /* Word()ִ����ϣ�������һ����״ֵ̬ */
        C_WillRun = false;
        A_WillRun = true;
        /*������һ���߳�*/
        this.notifyAll();

    }
}
