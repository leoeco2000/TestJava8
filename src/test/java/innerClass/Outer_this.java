package test.java.innerClass;

class OuterClass {
  private int testId = 1;

  public OuterClass() {
    System.out.println(testId);
  }

  class InnerClass {
    private int myTestId = 0;

    public InnerClass() {
      this.myTestId = OuterClass.this.testId + 1;
      System.out.println(myTestId);
    }
  }
}


public class Outer_this {

  public static void main(String[] args) {
    OuterClass out = new OuterClass();
    OuterClass.InnerClass in = out.new InnerClass();
  }
}
