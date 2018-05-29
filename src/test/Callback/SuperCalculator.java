package test.Callback;

public class SuperCalculator
{
    public void add(int a, int b, DoJob  customer)
    {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
    
//	private int kk = 5;
//	class test1 {
//		public void get(){
//			System.out.println(kk);
//			kk--;
//			System.out.println(kk);
//		}
//	}
}