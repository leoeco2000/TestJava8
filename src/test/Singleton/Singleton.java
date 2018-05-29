package test.Singleton;

public class Singleton {
//	private static Singleton instance=null;
	   private Singleton(){
		   System.out.println("new");
	   }
//	   public static synchronized Singleton getInstance(){
//	       if(instance==null){
//	    	   System.out.println("new");
//	           instance=new Singleton();
//	       }
//	       return instance;
//	   }
	   private static class SingletonHolder{
	       private final static Singleton instance=new Singleton();
	   }
	   public static Singleton getInstance(){
	       return SingletonHolder.instance;
	   }

	   public static void main(String[] args) {
		   int i=1;
		   for(;i<100;i++) {
			   new Thread() {
				   public void run() {
					   getInstance();
				   }
			   }.start();
		   }
		   System.out.println("over");
	   }
	   
       /*
        * 第二种方式：
        * 1.将Runnable的子类对象传递给Thread的构造方法
        * 2.重写run方法
        * 3.将执行的代码写在run方法中，最后我们开启线程
        */
//       new Thread(new Runnable() {
//           public void run() {
//               for (int i = 0; i < 10; i++) {
//                   System.out.println("bbbbbbbbb");
//               }
//           }
//       }).start();//开启线程
       
       
       
       
       
}
