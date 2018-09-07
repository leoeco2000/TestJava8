package test.java.innerClass;

class HideTest {
  private class Person implements CanRun {
 
    @Override
    public void run() {
      System.out.println("run");
    }
  }
 
  public Person getPerson() {
    return new Person();
  }
}
 
class myTest extends HideTest {
  //没有任何需要重写的方法
}
 
interface CanRun {
  void run();
}


public class HideImplementByInnerAndInterface {
  public static void main(String[] args) {
    CanRun person = new HideTest().getPerson();
    person.run();
  }
}
