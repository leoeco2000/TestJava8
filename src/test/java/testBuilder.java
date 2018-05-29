package test.java;

class User {
	static {
		System.out.println("outer static");
	}
	private final String firstName; // 必传参数
	private final String lastName; // 必传参数
	private final int age; // 可选参数
	private final String phone; // 可选参数
	private final String address; // 可选参数

	public User(UserBuilder builder) {
		System.out.println("outer contruct");
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public static class UserBuilder {
		static {
			System.out.println("innder static");
		}
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;

		public UserBuilder(String firstName, String lastName) {
			System.out.println("innder contruct");
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}

		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}

		public User build() {
			User user = new User(this);
			if (user.getAge() > 120) {
//				throw new IllegalStateException("Age out of range"); // 线程安全
				System.out.println("Age out of range");
			}
			return user;
		}
	}
	public static void myTools() {
		System.out.println("myTools");
	}
}

class MyUser extends User{
	public MyUser(UserBuilder builder) {
		super(builder);
	}

	@Override
	public String getFirstName() {
		return "this is : " + this.getFirstName();
	}
}

public class testBuilder {
	public static void main(String[] args) {
		User.myTools();
		User o = new User.UserBuilder("first", "last").age(121).build();
		System.out.println(o.getAge() + ":" + o.getFirstName());
	}
}