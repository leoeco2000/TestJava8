package temp;

import java.util.ArrayList;
import java.util.List;

class Super {
  public Super() {
    overrideMe();
  }

  public void overrideMe() {}
}


public class Sub extends Super {
  private List<Object> list = new ArrayList<>();

  Sub() {}

  @Override
  public void overrideMe() {
    System.out.println(list.size());
  }

  public static void main(String[] args) {
    Super s = new Sub();
    s.overrideMe();
  }
}
