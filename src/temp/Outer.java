package temp;

public class Outer{
  public void run(){ System.out.println("run"); }
  public void fly(){ System.out.println("fly"); }
  public void think(){ System.out.println("think"); }

  public class Runner implements Runnable1 {
    public void run() {
      Outer.this.run();
    }
  }
  public class Flyer implements Flyable {
    public void fly() {
      Outer.this.fly();
    }
  }
  public class Thinker implements Thinkable {
    public void think() {
      Outer.this.think();
    }
  }

  public Runner getRunner(){return new Runner();}
  public Flyer getFlyer(){return new Flyer();}
  public Thinker getThinker(){return new Thinker();}
  
  public static void main(String[] args) {
    Outer outer = new Outer();
    outer.getRunner().run();
    outer.getFlyer().fly();
    outer.getThinker().think();
  }
}

interface Runnable1{ public void run(); }
interface Flyable{ public void fly(); }
interface Thinkable{ public void think(); }