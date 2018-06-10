package test.java8Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
  public static int addUp(Stream<Integer> numbers) {
    return numbers.reduce(0, (acc, element) -> acc + element);
  }

  public static void main(String[] args) {
    class Track {
      String name;
      int length;

      Track(String name, int length) {
        this.name = name;
        this.length = length;
      }

      public int getLength() {
        return this.length;
      }
    }
    List<Track> tracks = Arrays.asList(
        new Track("Bakai", 524),
        new Track("Violets for YourFurs", 378),
        new Track("Time Was", 451));

    List<String> list =  tracks.stream().map(track -> track.name + track.getLength()).collect(Collectors.toList());
    list.stream().forEach(track -> System.out.println(track));

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



  }
}
