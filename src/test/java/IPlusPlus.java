package test.java;

public class IPlusPlus {
  public static void main(String[] args) {
    Integer i = null;
//    (++i) = 2;
    new Thread();
    
  }
}


/**
 * 
因为Java采取了中间变量缓存机制！
在java中，执行自增运算时，会为每一个自增操作分配一个临时变量，如果是前缀加（++i），
就会“先自加1后赋值（给临时变量）”；
如果是后缀加(i++)，就会“先赋值（给临时变量）后自加1”。
运算最终使用的，并不是变量本身，而是被赋了值的临时变量。
}

 * 
 * */
