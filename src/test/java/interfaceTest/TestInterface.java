package test.java.interfaceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestInterface {

  public static void main(String[] args) {
//    Integer[] arr  = new Integer[3];
//    List<Integer> list = Arrays.asList(1,2,3);
//    Integer[] arr1 = list.toArray(new Integer[0]);
//    list = Arrays.asList(arr1);
//    System.out.println(arr1);
//    System.arraycopy(arr1, 0, arr, 0, 2);
//    System.out.println(arr.length);
//    
//    Integer[] arrcopy = Arrays.copyOf(arr, 2);
//    Arrays.asList(arrcopy).forEach(System.out::print);
    
    Boolean re = getCheckResult((int) Math.pow(2, 31), check((int) Math.pow(73, 5)));
    System.out.println(re);
  }
  private static Predicate<Integer> check(int b) {
    return a -> a > b;
  }

  private static Boolean getCheckResult(int a, Predicate<Integer> predicate) {
    if (predicate.test(a)) {
        return true;
    }
    return false;
  }

}

//interface TestInterfaceA {
//  String pri_key = "guess what the private key is";
//
//  int add(int x, int y);
//
//  String encryt(byte[] result);
//
//  String get();
//}

