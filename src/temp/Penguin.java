package temp;

public class Penguin {
  public void run(){ // do something 
  }

  public void swim(){ //do something 
  }

    public class Running implements Runnable{ 
        public void run(){Penguin.this.run();} 
    }
    public class Swimming implements Runnable{ 
        public void run(){Penguin.this.swim();} 
    }

  public Running getRunner() {
    return new Running();
  }

  public Swimming getSwimmer() {
    return new Swimming();
  }
}

interface Runnable {
  public void run();
}
