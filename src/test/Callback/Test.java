package test.Callback;

public class Test
{
    public static void main(String[] args)
    {
//    	String[] arr = new String[1];
//    	... ( () -> a[0] = "a" );
//    	arr[0];
//    	
//    	new SuperCalculator().new test1().get();
//    	new SuperCalculator().new test1().get();

        int a = 56;
        int b = 31;
        int c = 26497;
        int d = 11256;
        Student s1 = new Student("Ð¡Ã÷");
        Seller s2 = new Seller("ÀÏÆÅÆÅ");
        
        s1.callHelp(a, b);
        s2.callHelp(c, d);
    }
}