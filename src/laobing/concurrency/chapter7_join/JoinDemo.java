package laobing.concurrency.chapter7_join;

public class JoinDemo {
    static int result = 0;
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
        	final int j= i;
        	Thread st = new Thread(new Runnable() {
        	    public void run() {
        	        /*Ä£ÄâºÄÊ±²Ù×÷*/
        	        try {
        	        	result = result + j;
        	            Thread.sleep(20);
        	        } catch (InterruptedException e) {
        	            e.printStackTrace();
        	        }
        	    }
        	});
        	st.start();
        	try {
				st.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.print(result);
    }
}
