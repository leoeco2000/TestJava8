package test.Comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DomainComparator implements Comparator<Domain>
{
    //可以自定义compare方法
   public int compare(Domain domain1, Domain domain2)
   {
       if (domain1.getStr().compareTo(domain2.getStr()) > 0)
           return 1;
       else if (domain1.getStr().compareTo(domain2.getStr()) == 0)
           return 0;
       else 
           return -1;
   }
   
   public static void main(String[] args)
   {
      Domain d1 = new Domain("3");
      Domain d2 = new Domain("3");
      Domain d3 = new Domain("2");
      Domain d4 = new Domain("4");
      DomainComparator dc = new DomainComparator();
      System.out.println(dc.compare(d1, d2));
      System.out.println(dc.compare(d1, d3));
      System.out.println(dc.compare(d1, d4));
      
   }

}


