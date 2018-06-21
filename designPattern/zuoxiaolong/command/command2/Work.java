package zuoxiaolong.command.command2;

public class Work {

  public static void main(String[] args) {
      Programmer xiaozuo = new Programmer("С��");
      ProductManager productManager = new ProductManager(xiaozuo);
      
      Salesman salesmanA = new Salesman("A",productManager);
      Salesman salesmanB = new Salesman("B",productManager);
      Salesman salesmanC = new Salesman("C",productManager);
      Salesman salesmanD = new Salesman("D",productManager);
      
      salesmanA.putDemand();
      salesmanB.putDemand();
      salesmanB.putBug();
      salesmanC.putDemand();
      salesmanC.putProblem();
      salesmanD.putDemand();
      
      System.out.println("��һ���Ʒ�����������");
      productManager.assign();
      productManager.printTaskList();
      System.out.println("�ڶ����Ʒ�����������");
      productManager.assign();
      productManager.printTaskList();
  }
  
}