package test.java.generics;

class Box<T> {
  private T object;

  public void set(T object) {
    this.object = object;
  }

  public T get() {
    return object;
  }
}


class Fruit { }
class Banana extends Fruit { }
class Apple extends Fruit { }


public class TransType {
  public static void main(String[] args) {
    Box box2 = new Box();
    box2.set(new Apple());
    // ����һ��Box���󣬲������Ͳ��������ֻ�ȡ�����ʱ����Ҫǿ��ת��
    Apple apple2 = (Apple) box2.get();

    Box<Apple> box = new Box<Apple>();
    box.set(new Apple());
    // ����һ��Box���󣬴����Ͳ�������ȡ�����ʱ��Ͳ���Ҫǿ��ת��
    Apple apple = box.get();
  }
}
