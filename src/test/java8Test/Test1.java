package test.java8Test;

import static java.util.stream.Collectors.groupingBy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import test.java8Test.Java8InAction.chap5.Trader;
import test.java8Test.Java8InAction.chap5.Transaction;

public class Test1 {
  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
    new Transaction(brian, 2011, 300),
    new Transaction(raoul, 2012, 1000),
    new Transaction(raoul, 2011, 400),
    new Transaction(mario, 2012, 710),
    new Transaction(mario, 2012, 700),
    new Transaction(alan, 2012, 950)
    );
    
    Map<Integer, Map<Integer,List<Transaction>>> map = 
        transactions.stream()
                    .collect(groupingBy(t ->
                          {
                            if(t.getYear() <= 2011) return 2011;
                            else return 2012;
                          },
                          groupingBy(s ->
                          {
                            if(s.getValue() <= 900) return 900;
                            else return 901;
                          })
                        )
                        );
//    System.out.println(map);
    
    List<Integer> list = Arrays.asList(1,2,4,2,8,5);
//    Collections.sort(list, new Comparator<Integer>() {
//      @Override
//      public int compare(Integer o1, Integer o2) {
//        return o1.compareTo(o2);
//      }
//    });
    
//    Comparator<Integer> c = (o1,o2)->o1.compareTo(o2);
//    list.stream()
//    .sorted(c)
//    .distinct()
//    .forEach(System.out::println);
//    Supplier<Long> sp = new Supplier<Long>() {
//      long value = 0;
//
//      public Long get() {
//          this.value = this.value + 1;
//          return this.value;
//      }
//    };
//    Stream<Long> natural = Stream.generate(new Supplier<Long>() {
//      long value = 0;
//
//      public Long get() {
//          this.value = this.value + 1;
//          return this.value;
//      }
//    });
//    natural.limit(10).forEach(System.out::println);
//    
    String[] arr1 = {"a", "b", "c", "d"};
    String[] arr2 = {"e", "f", "c", "d"};
    String[] arr3 = {"h", "j", "c", "d"};
   // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
    Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
    Stream.iterate(0,x->x+1).limit(10).forEach(System.out::println);
  }
}
class NaturalSupplier implements Supplier<Long> {

  long value = 0;

  public Long get() {
      this.value = this.value + 1;
      return this.value;
  }
}