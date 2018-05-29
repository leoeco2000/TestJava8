package laobing.concurrency.chapter15_threadlocal;

import java.util.Random;

class Customer {
	private int userid;
	private int account;

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	/* ��ʹ���û�������� */
	public Customer(int userid, int account) {
		this.userid = userid;
		this.account = account;
	}

	/* ���Ӳ�����ͬ�����������������߳���ͬʱ������ͬ�û����ʻ� */
	public synchronized void increase(int m) {
		this.account += m;
		System.out.println("�û�[id:" + String.valueOf(userid) + "]����" + String.valueOf(m) + "�㣬�������" + account + "��");
	}

	/* ���ٲ�����ͬ�����������������߳���ͬʱ������ͬ�û����ʻ� */
	public synchronized void decrease(int m) {
		if ((account - m) >= 0) {
			this.account -= m;
			System.out
					.println("�û�[id:" + String.valueOf(userid) + "]����" + String.valueOf(m) + "�㣬�������" + account + "��");
		} else {
			System.err.println("�û�[id:" + String.valueOf(userid) + "]���ֲ��㣬��ǰ����" + String.valueOf(account) + "�㣬�����һ���Ʒ��"
					+ String.valueOf(m) + "��");
		}
	}
}

/*
 * ����һ����������������һ��������ȡCustomer��ʵ��
 * ����������ȡ�Ķ����ǵ���ģʽ
 * ��ʼ��Customer���ʻ������ó�ʼ����Ϊ1000
 */
//class Container {
//    private static Customer customer;
//    public static Customer getCustomer() {
//        if(customer==null){
//            customer=new Customer(0, 1000);//Ӳ����
//        }
//        return customer;
//    }
//}
class Container {
//	private static Customer customer = new Customer(0, 3000);
    private static ThreadLocal<Customer> Local_Customer = new ThreadLocal<Customer>(){
        @Override
        protected Customer initialValue() {
            /*����customer��ǰΪnull������ʵ��ÿ���̻߳�õ���һ��null���� */
            return new Customer(0, 3000);
        }
    };
    public static Customer getCustomer() {
//        if(Local_Customer.get()==null){
//            Local_Customer.set(new Customer(0, 3000));//Ӳ����
//        }
        return Local_Customer.get();
    }
}

public class ThreadLocalTest {

	// static Customer someone=new Customer(0,3000);

	public static void main(String[] args) {
		for (int m = 0; m < 2; m++) {
			new Thread() {
				public void run() {
					/* ��ʼ���û������ó�ʼ�˻����� 1ǧ */
//					Customer someone = new Customer(new Random().nextInt(1000), 3000);
					Customer someone = Container.getCustomer();
					someone.setUserid(new Random().nextInt(1000));
					
					/* ģ���������2�λ��� */
					for (int i = 0; i < 2; i++) {
						someone.increase(new Random().nextInt(500));
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					/* ģ��һ���Ʒ���Ļ���2�� */
					for (int i = 0; i < 2; i++) {
						someone.decrease(new Random().nextInt(3700));
						try {
							Thread.sleep(1200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(Thread.currentThread().getName() + "�߳����û�[id:" + someone.getUserid() + "] ���: "
							+ someone.getAccount());
				}
			}.start();
		}
	}
}
