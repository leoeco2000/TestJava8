package test.Comparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Domain implements Comparable<Domain> {
  private String str;

  public Domain(String str) {
    this.str = str;
  }

  public int compareTo(Domain domain) {
    if (this.str.compareTo(domain.str) > 0)
      return 1;
    else if (this.str.compareTo(domain.str) == 0)
      return 0;
    else
      return -1;
  }

  public String getStr() {
    return str;
  }

  public void test1_(){
      List<Integer> list = this.asList(ArrayList::new ,1,2,3,4,5);
      list.forEach(System.out::println);
  }

  public  <T> List<T> asList(MyCrator<List<T>> creator,T... a){
    List<T> list =  creator.create();
    for (T t : a)
        list.add(t);
    return list;
}

  public static void main(String[] args) {
    Domain d1 = new Domain("3");
    Domain d2 = new Domain("3");
    Domain d3 = new Domain("2");
    Domain d4 = new Domain("4");
    System.out.println(d1.compareTo(d2));
    System.out.println(d1.compareTo(d3));
    System.out.println(d1.compareTo(d4));

    List<Domain> list = new ArrayList<>();
    list.add(d1);
    list.add(d2);
    list.add(d3);
    list.add(d4);
    list.sort(null);
    for (Domain d : list) {
      System.out.println(d.getStr());
    }
    
    //java8
    List<Domain> list2 = new ArrayList<Domain>() {
      {
        add(d1);
        add(d2);
        add(d3);
        add(d4);
      }
    };
    list2.sort(
        (Domain do1, Domain do2) -> do1.compareTo(do2));
//    list2.sort(Domain::compareTo);
    for (Domain d : list2) {
      System.out.println(d.getStr());
    }
    
  }

}
interface MyCrator<T extends List<?>>{
  T create();
}