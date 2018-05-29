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
        /* ģ��ܶ��̲߳�������ʱ��ɵľ��� */
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(
            "Factorial�����" + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));

      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {
          calculator.getAccu(10);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
        /* ģ��ܶ��̲߳�������ʱ��ɵľ��� */
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(
            "Accu�����" + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
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
    System.out.println("���ռ�����" + String.valueOf(sum));
  }
}


/* ������������ü�����ͳһ���������� */
class Calculator {
  // public static int result = 0;
  public static Map<Thread, Integer> DataContainer =
      Collections.synchronizedMap(new HashMap<Thread, Integer>());

  void getFactorial(int n) throws Exception {
    /* ģ��׳�������Ҫ7�� */
    try {
      Thread.sleep(7000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      /* ���н׳����� */
      int result = 0;
      result = Factorial.GetResult(n);
      DataContainer.put(Thread.currentThread(), result);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    System.out.println("�߳�" + Thread.currentThread().getName() + "��������"
        + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
  }

  void getAccu(int n) throws Exception {
    /* ģ�����������Ҫ5�� */
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      int result = 0;
      result = Accu.GetResult(n);/* ����������� */
      DataContainer.put(Thread.currentThread(), result);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    System.out.println("�߳�" + Thread.currentThread().getName() + "��������"
        + String.valueOf(Calculator.DataContainer.get(Thread.currentThread())));
  }
}


/* ����׳˴�����Factorial ������ģ��ʵ�֣�ʵ�ʿ�������Ҫ�ô���������BigInteger���� */
class Factorial {
  public static int GetResult(int n) throws Exception {
    if (n < 0 || n > 10) {
      throw new Exception("error:������ѣ����벻��С����,Ҳ���ܴ���10");
    }
    if (n <= 2) {
      return n;
    }
    return n * GetResult(n - 1);
  }
}


/* ������ʹ�����Accu */
class Accu {
  public static int GetResult(int n) throws Exception {
    if (n < 0 || n >= 1000) {
      throw new Exception("error:������ѣ����벻��С����,Ҳ���ܴ���1000");
    }
    if (n <= 1) {
      return n;
    }
    return n + GetResult(n - 1);
  }
}
