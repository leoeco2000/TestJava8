package test.java8Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestConsumer {

  public static void main(String[] args) {
    Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
    c.accept("CONSUMER");


    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // 使用匿名函数形式
    numbers.forEach(new Consumer<Integer>() {
      @Override
      public void accept(Integer integer) {
        System.out.println(integer);
      }
    });

    // 使用Lambda
    List<Integer> numbers2 = Arrays.asList(11, 21, 31, 41, 51, 61, 71, 81, 91, 110);
    Consumer<List> consumer = (x) -> System.out.println(x);
    consumer.accept(numbers2);



    List<Student> students = Arrays.asList(new Student(1, 3, "John"), new Student(2, 4, "Jane"),
        new Student(3, 3, "Jack"));

    Consumer<Student> raiser = e -> {
      e.gpa = (long) (e.gpa * 2);
    };

    raiseStudents(students, System.out::println);
    raiseStudents(students, raiser.andThen(System.out::println));// 先执行raiser 然后再去执行输出操作
  }

  private static void raiseStudents(List<Student> employees, Consumer<Student> fx) {
    for (Student e : employees) {
      fx.accept(e);
    }
  }

}
