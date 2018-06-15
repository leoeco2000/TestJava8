package test.java8Test;

import static java.util.stream.Collectors.groupingBy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    System.out.println(map);
    
    List<Integer> list = Arrays.asList(1,2,4,2,8,5);
//    Collections.sort(list, new Comparator<Integer>() {
//      @Override
//      public int compare(Integer o1, Integer o2) {
//        return o1.compareTo(o2);
//      }
//    });
    
    Comparator<Integer> c = (o1,o2)->o1.compareTo(o2);
    list.stream()
    .sorted(c)
    .distinct()
    .forEach(System.out::println);
    
  }
}
