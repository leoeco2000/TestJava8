package test.java.innerClass;

import java.util.Date;
import test.java.innerClass.Outer.InnerStatic;

abstract class Person {
  public abstract void work();
}

class Outer {
  private int id;
  int x;
  static int y;
  static void fun3() {

  }

  public void fun1() {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
      }
    });
    thread.run();
  }

  // ��ͨ�ڲ���
  class Inner {
    final static int a = 2;

    public void fun1() {
      fun3();
      System.out.println("x = " + x);
      System.out.println("y = " + y);
      System.out.println("id = " + id);
    }

    /*
     * //�������static methods can only be declared in a static or top level type public static void
     * fun2(){
     * 
     * } static class A{
     * 
     * } //�������Cannot define static initializer in inner type Outer.Inner static{
     * 
     * }
     */
  }

  // ��̬�ڲ���
  static class InnerStatic {
    static {
    }

    public void fun1() {
      System.out.println("y = " + y);
    }

    public static void fun2() {}
  }

  // �����ڲ���
  Person p = new Person() {

    @Override
    public void work() {
      System.out.println("�����ڲ���");

    }
  };

}

public class TestInnerClassNO_USE extends Date {
  public static void main(String[] args) {
    // ��ͨ�ڲ���ʵ����
    Outer.Inner inner = new Outer().new Inner();
    // ��̬�ڲ���ʵ����
    Outer.InnerStatic inner1 = new Outer.InnerStatic();
    InnerStatic inner11 = new InnerStatic();
  }
}
