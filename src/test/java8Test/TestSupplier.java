package test.java8Test;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class TestSupplier {
  public static SunPower produce(Supplier<SunPower> supp) {
    return supp.get();
  }

  private static Employee employee(Supplier<Employee> supplier) {
    return supplier.get(); // 在没有写构造函数的时候 就需要重写toString方法
  }

  public static Student employeeMaker() {
    return new Student(1, 5, "John");
  }

  public static void main(String[] args) {
    SunPower power = new SunPower();
    SunPower p1 = produce(() -> power);// 只会初始化一次 因此只会输出一个结果
    SunPower p2 = produce(() -> power);
    System.out.println("Check the same object? " + Objects.equals(p1, p2));

    System.out.println(employee(Employee::new));// A EMPLOYEE

    Supplier<Student> studentGenerator = TestSupplier::employeeMaker;

    for (int i = 0; i < 10; i++) {
      System.out.println("#" + i + ": " + studentGenerator.get());
    }

    BooleanSupplier bs = () -> true;//没有参数  但是有返回结果
    System.out.println(bs.getAsBoolean());

    int x = 0, y= 1;
    bs = () -> x > y;
    System.out.println(bs.getAsBoolean());

  }

}


class SunPower {
  public SunPower() {
    System.out.println("Sun Power initialized..");
  }
}


class Employee {
  @Override
  public String toString() {
    return "A EMPLOYEE";
  }
}


