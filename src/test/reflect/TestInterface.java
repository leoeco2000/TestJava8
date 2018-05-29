package test.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestInterface<A, B> implements MyInterfaceC<A, B>, MyInterfaceA, MyInterfaceB {

  /*
   * һ.˵��
   * 
   * 1.Type[] getGenericInterfaces
   * 
   * ��Type����ʽ���ر���ֱ��ʵ�ֵĽӿ�.�����Ͱ����˷��Ͳ�����Ϣ
   * 
   * 2.Class[] getInterfaces
   * 
   * ���ر���ֱ��ʵ�ֵĽӿ�.���������Ͳ�����Ϣ
   */
  private A a;

  private B b;

  // MyInterfaceC
  @Override
  public A getA() {
    return a;
  }

  @Override
  public B getB() {
    return b;
  }

  // MyInterfaceA
  @Override
  public void sayHello() {}

  // MyInterfaceB
  public String print() {
    return "";
  }

  public static void main(String args[]) throws Exception {

    Type[] ts = TestInterface.class.getGenericInterfaces();

    System.out.println("======getGenericInterfaces======:");

    for (Type t : ts) {
      System.out.println(t);
      if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
        System.out.print("----------->getActualTypeArguments:");
        for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
          System.out.print(t1 + ",");
        }
        System.out.println();
      }
    }

    System.out.println();
    System.out.println("======getInterfaces======:");
    for (Class c : TestInterface.class.getInterfaces()) {
      System.out.println(c.getName());
    }

  }

}
