package temp.command;

public class Programmer {

  private String name;

  public Programmer(String name) {
      super();
      this.name = name;
  }

  public String getName() {
      return name;
  }
  
  public void handleDemand(){
      System.out.println( name + "����������");
  }
  
  public void handleBug(){
      System.out.println( name + "����bug");
  }
  
  public void handleProblem(){
      System.out.println( name + "������������");
  }
  
}