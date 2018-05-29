package test.java.clone;

class Person1 {
	private String name;
	private String sex;
	private int age;

	public Person1(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Person1(Person1 p) { // 拷贝构造方法，复制对象
		this.name = p.name;
		this.sex = p.sex;
		this.age = p.age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

class Asian {
	private String skin;
	Person1 person;

	public Asian(String skin, Person1 person) {
		this.skin = skin;
		this.person = person; // 引用赋值
	}

	public Asian(Asian asian) { // 拷贝构造方法，复制对象
		// TODO
		// 浅拷贝：使用一个已知实例对新创建实例的成员变量逐个赋值，这个方式被称为浅拷贝。
		// this(asian.skin,asian.person);
		// 深拷贝：当一个类的拷贝构造方法，不仅要复制对象的所有非引用成员变量值，
		// 还要为引用类型的成员变量创建新的实例，并且初始化为形式参数实例值。这个方式称为深拷贝
		this(asian.skin, new Person1(asian.person));
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public Person1 getPerson() {
		return person;
	}

	public void setPerson(Person1 person) {
		this.person = person;
	}
}

public class testClone {
	public static void main(String[] args) {
		Person1 p1 = new Person1("李四", "mam", 23);
		Person1 p2 = new Person1(p1);

		Asian a1 = new Asian("yellow", new Person1("李四", "mam", 23));
		Asian a2 = new Asian(a1);

		p1.setAge(25);
		System.out.println(p2.getAge());
		a1.getPerson().setAge(24);
		System.out.println(a1.getPerson().getAge());
		System.out.println(a2.getPerson().getAge());
	}
}
