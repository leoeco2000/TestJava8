package test.java8Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
  public static int addUp(Stream<Integer> numbers) {
    return numbers.reduce(0, (acc, element) -> acc + element);
  }

  public static void main(String[] args) {

    // IntStream.range(1, 4)
    // .forEach((int i) -> System.out.println(i));

    List<Integer> list = Arrays.asList(1, 2, 3);
    Integer[] arr = list.toArray(new Integer[0]);
    List<Integer> list2 = Arrays.asList(arr);
    String arrstr = "123";
    int intv = Integer.parseInt(arrstr);
    // System.out.println(intv);
    char[] chararr = arrstr.toCharArray();
    List<Integer> intlist = new ArrayList<>();;
    for (char cr : chararr) {
      // intlist.add(cr - '0');
      intlist.add(Character.getNumericValue(cr));
    }
    intlist.forEach(System.out::print);

    Stream.iterate(1,

  item -> item+1).limit(10).forEach(System.out::println);

  // Collections.unmodifiableCollection(list)
  // .add(4);
  // list.forEach(i -> System.out.println(i));

  // class Track {
  // String name;
  // int length;
  //
  // Track(String name, int length) {
  // this.name = name;
  // this.length = length;
  // }
  //
  // public int getLength() {
  // return this.length;
  // }
  // }
  // List<Track> tracks = Arrays.asList(
  // new Track("Bakai", 524),
  // new Track("Violets for YourFurs", 378),
  // new Track("Time Was", 451));
  //
  // List<String> list = tracks.stream().map(track -> track.name +
  // track.getLength()).collect(Collectors.toList());
  // list.stream().forEach(track -> System.out.println(track));

  // List<String> list = new ArrayList<>();
  // list.add("a");
  // list.add("b");
  // list.add("c");
  // int sum = StreamTest.addUp(Stream.of(1, 2, 3));
  // System.out.println(sum);



  // long a = list.stream().filter(str -> {
  // System.out.println(str);
  // return true;
  // }).count();
  // System.out.print(a);
  //
  //
  // List<String> collected = Stream.of("a", "b", "hello").map(string -> string.toUpperCase())
  // .collect(Collectors.toList());
  // assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
  //
  //
  // List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
  // .flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
  // assertEquals(Arrays.asList(1, 2, 3, 4), together);
  //

  // Track shortestTrack =
  // tracks.stream().min(Comparator.comparing(track -> track.getLength())).get();
  // System.out.println(shortestTrack.length);
  // assertEquals(tracks.get(1), shortestTrack);

  System.out.println("Using Java 7: ");

  // Count empty strings
  List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "bbbb",
      "jkl");System.out.println("List: "+strings);
  long count = getCountEmptyStringUsingJava7(strings);

  System.out.println("Empty Strings: "+count);count=

  getCountLength3UsingJava7(strings);

    System.out.println("Strings of length 3: " + count);

    //Eliminate empty string
    List<String> filtered = deleteEmptyStringsUsingJava7(strings);
    System.out.println("Filtered List: " + filtered);

    //Eliminate empty string and join using comma.
    String mergedString = getMergedStringUsingJava7(strings, ", ");
    System.out.println("Merged String: " + mergedString);

    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    //get list of square of distinct numbers
    List<Integer> squaresList = getSquares(numbers);
    System.out.println("Squares List: " + squaresList);

    List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
    System.out.println("List: " + integers);
    System.out.println("Highest number in List : " + getMax(integers));
    System.out.println("Lowest number in List : " + getMin(integers));
    System.out.println("Sum of all numbers : " + getSum(integers));
    System.out.println("Average of all numbers : " + getAverage(integers));
    System.out.println("Random Numbers: ");

    //print ten random numbers
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
        System.out.println(random.nextInt());
    }



    System.out.println("Using Java 8: ");
    System.out.println("List: " + strings);

    count = strings.stream().filter(

  string -> string.isEmpty()).count();// filter里面是定义好的规则 其参数就是predicate
  System.out.println("Empty Strings: "+count);

  count=strings.stream().filter(

  string -> string.length()==3).count();System.out.println("Strings of length 3: "+count);

  /**
   * Collectors are used to combine the result of processing on the elements of a stream. Collectors
   * can be used to return a list or a string(list和String都是可以的)
   */
  filtered=strings.stream().filter(

  string -> !string.isEmpty()).collect(Collectors.toList());System.out.println("Filtered List: "+filtered);

  mergedString=strings.stream().filter(

  string -> !string.isEmpty()).collect(Collectors.joining(", "));System.out.println("Merged String: "+mergedString);

  /**
   * The map method is used to map each element to its corresponding result key-value(结果)
   */
  squaresList=numbers.stream().map(

  i -> i*i).distinct().collect(Collectors.toList());System.out.println("Squares List: "+squaresList);System.out.println("List: "+integers);

  /**
   * With Java 8, statistics collectors are introduced to calculate all statistics when stream
   * processing is being done. IntSummaryStatistics类中已经有最大值、最小值、数量、平均值等函数
   */
  IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

  System.out.println("Highest number in List : "+stats.getMax());System.out.println("Lowest number in List : "+stats.getMin());System.out.println("Sum of all numbers : "+stats.getSum());System.out.println("Average of all numbers : "+stats.getAverage());System.out.println("Random Numbers: ");

  /**
   * Stream has provided a new method forEach to iterate each element of the stream. The following
   * code segment shows how to print 10 random numbers using forEach.
   */

  /**
   * The limit method is used to reduce the size of the stream.
   */

  /**
   * The sorted method is used to sort the stream
   */
  random.ints().limit(10).sorted().forEach(System.out::println);// 返回的是10个已经排好顺序的伪随机int数
  random.doubles().limit(10).forEachOrdered(System.out::println);// 返回的是10个伪随机double数



  /**
   *
   * two methods to generate a Stream stream() − Returns a sequential stream considering collection
   * as its source. parallelStream() − Returns a parallel Stream considering collection as its
   * source.
   */
  count=strings.parallelStream().filter(

  string -> string.isEmpty()).count();System.out.println("Empty Strings: "+count);

}



  // 返回长度为0的字符串数量
  private static int getCountEmptyStringUsingJava7(List<String> strings) {
    int count = 0;
    for (String string : strings) {
      if (string.isEmpty()) {
        count++;
      }
    }
    return count;
  }


  // 返回长度为3的字符串数量
  private static int getCountLength3UsingJava7(List<String> strings) {
    int count = 0;
    for (String string : strings) {
      if (string.length() == 3) {
        count++;
      }
    }
    return count;
  }

  // 去除空字符串
  private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
    List<String> filteredList = new ArrayList<String>();
    for (String string : strings) {
      if (!string.isEmpty()) {
        filteredList.add(string);
      }
    }
    return filteredList;
  }

  // 加入分隔符
  private static String getMergedStringUsingJava7(List<String> strings, String separator) {
    StringBuilder stringBuilder = new StringBuilder();// StringBuilder不是线程安全
    for (String string : strings) {
      if (!string.isEmpty()) {
        stringBuilder.append(string);
        stringBuilder.append(separator);
      }
    }
    String mergedString = stringBuilder.toString();
    return mergedString.substring(0, mergedString.length() - 2);
  }

  // 平方
  private static List<Integer> getSquares(List<Integer> numbers) {
    List<Integer> squaresList = new ArrayList<>();

    for (Integer number : numbers) {
      Integer square = new Integer(number.intValue() * number.intValue());

      if (!squaresList.contains(square)) {
        squaresList.add(square);
      }
    }
    return squaresList;
  }

  // 获取最大值
  private static int getMax(List<Integer> numbers) {
    int max = numbers.get(0);

    for (int i = 1; i < numbers.size(); i++) {

      Integer number = numbers.get(i);

      if (number.intValue() > max) {
        max = number.intValue();
      }
    }
    return max;
  }

  // 最小值
  private static int getMin(List<Integer> numbers) {
    int min = numbers.get(0);

    for (int i = 1; i < numbers.size(); i++) {
      Integer number = numbers.get(i);

      if (number.intValue() < min) {
        min = number.intValue();
      }
    }
    return min;
  }

  // 和
  private static int getSum(List numbers) {
    int sum = (int) (numbers.get(0));

    for (int i = 1; i < numbers.size(); i++) {
      sum += (int) numbers.get(i);
    }
    return sum;
  }

  // 平均值
  private static int getAverage(List<Integer> numbers) {
    return getSum(numbers) / numbers.size();
  }



}
