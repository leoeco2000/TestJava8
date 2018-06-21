package zuoxiaolong.command.command2;

public class Salesman {

  private String name;
  
  private ProductManager productManager;

//  public Salesman(String name) {
//      super();
//      this.name = name;
//  }
  
  public Salesman(String name, ProductManager productManager) {
      super();
      this.name = name;
      this.productManager = productManager;
  }

  public void putDemand(){
      System.out.println( "ҵ��Ա" + name + "���������");
      productManager.receive(new Demand(productManager.chooseProgrammer()));
  }
  
  public void putBug(){
      System.out.println( "ҵ��Ա" + name + "���bug");
      productManager.receive(new Bug(productManager.chooseProgrammer()));
  }
  
  public void putProblem(){
      System.out.println( "ҵ��Ա" + name + "�����������");
      productManager.receive(new Problem(productManager.chooseProgrammer()));
  }

  public String getName() {
      return name;
  }

  public ProductManager getProductManager() {
      return productManager;
  }

  public void setProductManager(ProductManager productManager) {
      this.productManager = productManager;
  }
}