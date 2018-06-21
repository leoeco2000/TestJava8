package zuoxiaolong.command;

public class Work{

  public static void main(String[] args) {
      Programmer xiaozuo = new Programmer("Ð¡×ó");
      
      Salesman salesmanA = new Salesman("A");
      salesmanA.putDemand(xiaozuo);
      
      Salesman salesmanB = new Salesman("B");
      salesmanB.putDemand(xiaozuo);
      
      Salesman salesmanC = new Salesman("C");
      salesmanC.putBug(xiaozuo);
      
      Salesman salesmanD = new Salesman("D");
      salesmanD.putProblem(xiaozuo);
  }
  
}