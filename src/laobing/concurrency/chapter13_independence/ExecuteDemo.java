package laobing.concurrency.chapter13_independence;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExecuteDemo {
  public static void main(String[] args) {
    final Calculator calculator = new Calculator();
    new Thread(new Runnable() {
      public void run() {
        try {
          calculator.getFactorial(10);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
        /* 模拟很多线程并发运行时造成的竞争 */
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(
            "Factorial结果：" + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));

      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {
          calculator.getAccu(10);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
        /* 模拟很多线程并发运行时造成的竞争 */
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(
            "Accu结果：" + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
      }
    }).start();
    while (Calculator.DataContainer.size() < 2) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Iterator<Map.Entry<Thread, Integer>> it = Calculator.DataContainer.entrySet().iterator();
    int sum = 0;
    while (it.hasNext()) {
      Map.Entry<Thread, Integer> entry = (Map.Entry<Thread, Integer>) it.next();
      Integer value = (Integer) entry.getValue();
      sum += value;
    }
    System.out.println("最终计算结果" + String.valueOf(sum));
  }
}


/* 定义计算器，让计算由统一的类来管理 */
class Calculator {
  // public static int result = 0;
  public static Map<Thread, Integer> DataContainer =
      Collections.synchronizedMap(new HashMap<Thread, Integer>());

  void getFactorial(int n) throws Exception {
    /* 模拟阶乘运算需要7秒 */
    try {
      Thread.sleep(7000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      /* 进行阶乘运算 */
      int result = 0;
      result = Factorial.GetResult(n);
      DataContainer.put(Thread.currentThread(), result);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    System.out.println("线程" + Thread.currentThread().getName() + "输出结果："
        + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
  }

  void getAccu(int n) throws Exception {
    /* 模拟求和运算需要5秒 */
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      int result = 0;
      result = Accu.GetResult(n);/* 进行求和运算 */
      DataContainer.put(Thread.currentThread(), result);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    System.out.println("线程" + Thread.currentThread().getName() + "输出结果："
        + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
  }
}


/* 定义阶乘处理类Factorial 这里是模拟实现，实际开发中需要用大数处理类BigInteger才行 */
class Factorial {
  public static int GetResult(int n) throws Exception {
    if (n < 0 || n > 10) {
      throw new Exception("error:玩玩而已，输入不能小于零,也不能大于10");
    }
    if (n <= 2) {
      return n;
    }
    return n * GetResult(n - 1);
  }
}


/* 定义求和处理类Accu */
class Accu {
  public static int GetResult(int n) throws Exception {
    if (n < 0 || n >= 1000) {
      throw new Exception("error:玩玩而已，输入不能小于零,也不能大于1000");
    }
    if (n <= 1) {
      return n;
    }
    return n + GetResult(n - 1);
  }
}
