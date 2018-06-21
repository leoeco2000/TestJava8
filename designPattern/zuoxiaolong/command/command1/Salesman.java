package zuoxiaolong.command.command1;

public class Salesman {

  private String name;

  public Salesman(String name) {
      super();
      this.name = name;
  }

  public String getName() {
      return name;
  }
  
  public void putDemand(ProductManager productManager,Programmer programmer){
      System.out.println( "ҵ��Ա" + name + "���������");
      productManager.receive(new Demand(programmer));
  }
  
  public void putBug(ProductManager productManager,Programmer programmer){
      System.out.println( "ҵ��Ա" + name + "���bug");
      productManager.receive(new Bug(programmer));
  }
  
  public void putProblem(ProductManager productManager,Programmer programmer){
      System.out.println( "ҵ��Ա" + name + "�����������");
      productManager.receive(new Problem(programmer));
  }
}