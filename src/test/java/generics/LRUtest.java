package test.java.generics;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class LRUtest {

  public static void main(String[] args) {
    FixedSizeLinkedHashMap map = new FixedSizeLinkedHashMap();
    map.put("1", 1);
    map.put("2", 2);
    map.put("3", 3);
    map.put("4", 4);
    map.put("5", 5);
    map.put("6", 6);

    map.forEach((key, value) -> {
      System.out.println(key + ":" + value);
    });
  }
}


class FixedSizeLinkedHashMap extends LinkedHashMap {

  private static final long serialVersionUID = 6918023506928428613L;
  private static int MAX_ENTRIES = 5;

  /**
   * 如果Map的尺寸大于设定的最大长度，返回true，再新加入对象时删除最老的对象
   * 
   * @param Map.Entry eldest
   * @return int
   */
  protected boolean removeEldestEntry(Map.Entry eldest) {
    return size() > MAX_ENTRIES;
  }
}
