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
    // 创建一个Box对象，不带泛型参数，发现获取对象的时候需要强制转换
    Apple apple2 = (Apple) box2.get();

    Box<Apple> box = new Box<Apple>();
    box.set(new Apple());
    // 创建一个Box对象，带泛型参数，获取对象的时候就不需要强制转换
    Apple apple = box.get();
  }
}
