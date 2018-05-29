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

	/* 初使化用户积分余额 */
	public Customer(int userid, int account) {
		this.userid = userid;
		this.account = account;
	}

	/* 增加操作。同步方法，避免其它线程在同时操作相同用户的帐户 */
	public synchronized void increase(int m) {
		this.account += m;
		System.out.println("用户[id:" + String.valueOf(userid) + "]存入" + String.valueOf(m) + "点，积分余额" + account + "点");
	}

	/* 减少操作。同步方法，避免其它线程在同时操作相同用户的帐户 */
	public synchronized void decrease(int m) {
		if ((account - m) >= 0) {
			this.account -= m;
			System.out
					.println("用户[id:" + String.valueOf(userid) + "]消费" + String.valueOf(m) + "点，积分余额" + account + "点");
		} else {
			System.err.println("用户[id:" + String.valueOf(userid) + "]积分不足，当前积分" + String.valueOf(account) + "点，期望兑换商品需"
					+ String.valueOf(m) + "点");
		}
	}
}

/*
 * 定义一个容器，该容器有一个方法获取Customer类实例
 * 假设容器获取的对像是单例模式
 * 初始化Customer类帐户，设置初始积分为1000
 */
//class Container {
//    private static Customer customer;
//    public static Customer getCustomer() {
//        if(customer==null){
//            customer=new Customer(0, 1000);//硬编码
//        }
//        return customer;
//    }
//}
class Container {
//	private static Customer customer = new Customer(0, 3000);
    private static ThreadLocal<Customer> Local_Customer = new ThreadLocal<Customer>(){
        @Override
        protected Customer initialValue() {
            /*由于customer当前为null，所以实际每个线程获得的是一个null对象 */
            return new Customer(0, 3000);
        }
    };
    public static Customer getCustomer() {
//        if(Local_Customer.get()==null){
//            Local_Customer.set(new Customer(0, 3000));//硬编码
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
					/* 初始化用户，设置初始账户积分 1千 */
//					Customer someone = new Customer(new Random().nextInt(1000), 3000);
					Customer someone = Container.getCustomer();
					someone.setUserid(new Random().nextInt(1000));
					
					/* 模拟操作增加2次积分 */
					for (int i = 0; i < 2; i++) {
						someone.increase(new Random().nextInt(500));
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					/* 模拟兑换商品消耗积分2次 */
					for (int i = 0; i < 2; i++) {
						someone.decrease(new Random().nextInt(3700));
						try {
							Thread.sleep(1200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(Thread.currentThread().getName() + "线程中用户[id:" + someone.getUserid() + "] 余额: "
							+ someone.getAccount());
				}
			}.start();
		}
	}
}
