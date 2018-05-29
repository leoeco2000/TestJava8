package test.reflect.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
	/**
	 * 打印类的信息，包括类的成员函数�?�成员变�?(只获取成员函�?)
	 * @param obj 该对象所属类的信�?
	 */
	public static void printClassMethodMessage(Object obj){
		//要获取类的信�?  首先要获取类的类类型
		Class c = obj.getClass();//传�?�的是哪个子类的对象  c就是该子类的类类�?
		//获取类的名称
		System.out.println("类的名称�?:"+c.getName());
		/*
		 * Method类，方法对象
		 * �?个成员方法就是一个Method对象
		 * getMethods()方法获取的是�?有的public的函数，包括父类继承而来�?
		 * getDeclaredMethods()获取的是�?有该类自己声明的方法，不问访问权�?
		 */
		Method[] ms = c.getMethods();//c.getDeclaredMethods()
		for(int i = 0; i < ms.length;i++){
			//得到方法的返回�?�类型的类类�?
			Class returnType = ms[i].getReturnType();
			System.out.print(returnType.getName()+" ");
			//得到方法的名�?
			System.out.print(ms[i].getName()+"(");
			//获取参数类型--->得到的是参数列表的类型的类类�?
			Class[] paramTypes = ms[i].getParameterTypes();
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
	/**
	 * 获取成员变量的信�?
	 * @param obj
	 */
	public static void printFieldMessage(Object obj) {
		Class c = obj.getClass();
		/*
		 * 成员变量也是对象
		 * java.lang.reflect.Field
		 * Field类封装了关于成员变量的操�?
		 * getFields()方法获取的是�?有的public的成员变量的信息
		 * getDeclaredFields获取的是该类自己声明的成员变量的信息
		 */
		//Field[] fs = c.getFields();
		Field[] fs = c.getDeclaredFields();
		for (Field field : fs) {
			//得到成员变量的类型的类类�?
			Class fieldType = field.getType();
			String typeName = fieldType.getName();
			//得到成员变量的名�?
			String fieldName = field.getName();
			System.out.println(typeName+" "+fieldName);
		}
	}
	/**
	 * 打印对象的构造函数的信息
	 * @param obj
	 */
	public static void printConMessage(Object obj){
		Class c = obj.getClass();
		/*
		 * 构�?�函数也是对�?
		 * java.lang. Constructor中封装了构�?�函数的信息
		 * getConstructors获取�?有的public的构造函�?
		 * getDeclaredConstructors得到�?有的构�?�函�?
		 */
		//Constructor[] cs = c.getConstructors();
		Constructor[] cs = c.getDeclaredConstructors();
		for (Constructor constructor : cs) {
			System.out.print(constructor.getName()+"(");
			//获取构�?�函数的参数列表--->得到的是参数列表的类类型
			Class[] paramTypes = constructor.getParameterTypes();
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
}
