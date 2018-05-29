package test.java.finalTest;

public class ObjectRef {

	public static void main(String[] args) {
        String str = "123";
        System.out.println(str);
        change(str);
        System.out.println(str);
        str = "ABC";
        System.out.println(str);
        
        int[] a = {1, 2};
        int[] b = a;
        b[1] = 5;
        System.out.println(a[1]);
        


		Person person = new Person("Jack");
		foo(person); // person 同样不会被改变。
    }

	public static void foo(Person person) {
	    person = new Person("Rose");
	}
	
	public static void change(String str) {
		str = "abc";
		System.out.println(str);
	}
}

class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
