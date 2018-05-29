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
        * �ڶ��ַ�ʽ��
        * 1.��Runnable��������󴫵ݸ�Thread�Ĺ��췽��
        * 2.��дrun����
        * 3.��ִ�еĴ���д��run�����У�������ǿ����߳�
        */
//       new Thread(new Runnable() {
//           public void run() {
//               for (int i = 0; i < 10; i++) {
//                   System.out.println("bbbbbbbbb");
//               }
//           }
//       }).start();//�����߳�
       
       
       
       
       
}
