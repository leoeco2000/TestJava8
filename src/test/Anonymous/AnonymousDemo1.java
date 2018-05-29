package test.Anonymous;

public class AnonymousDemo1
{
    public static void main(String args[])
    {
//    	int answer = 42;
//
//    	Thread t = new Thread(
//    	   () -> {
//    		   answer ++; // don't do this !
//    		   System.out.println("The answer is: " + answer)
//    	   }
//    	);
        new AnonymousDemo1().play();
    }
    

    private void play()
    {
        Dog dog = new Dog();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                while(dog.getAge()<5)
                {
                    // 过生日，年龄加一
                    dog.happyBirthday();
                    // 打印年龄
                    System.out.println(dog.getAge());
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("age: " + dog.getAge());
        // do other thing below when dog's age is increasing
        // ....
    }
}