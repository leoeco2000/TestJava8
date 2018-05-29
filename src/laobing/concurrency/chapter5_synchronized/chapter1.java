package laobing.concurrency.chapter5_synchronized;

/* 定义烹饪外部类 */
class Pan {
  /*
   * 烹饪方法，该方法输出步骤 加入synchronized关键字
   */
  public synchronized void Cook(String[] steps) {
    for (int i = 0; i < steps.length; i++) {
      /* 模拟竞争造成的线程等待，这样效果明显些 */
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.print(steps[i]);
    }
    System.out.println("");
  }

  /* 青椒炒肉制作步骤：a1.放肉，a2.放盐，a3.放辣椒 a4 a5.... */
  String[] steps_LaJiaoChaoRou = {"a1.", "a2.", "a3.", "a4.", "a5.", "a6.", "a7.", "a8.", "a9.",
      "a10.", "a11.", "a12.", "a13.", "a14.", "a15.", "a16.", "a17.", "a18.", "OK：辣椒炒肉"};
  /* 番茄炒蛋制作步骤：b1.放蛋，b2.放盐，b3.放番茄...... */
  String[] steps_FanQieChaoDan = {"b1.", "b2.", "b3.", "b4.", "b5.", "b6.", "OK:番茄炒蛋"};
}


public class chapter1 {
  public static void main(String[] args) {
    final Pan pan = new Pan();

    /* 线程1：老大炒青椒炒肉。 */
    new Thread() {
      public void run() {
        /* 为了看出错乱效果，这里用死循环，一段时间后手工点击停止运行按钮 */
        while (true) {
          try {
            /* 模拟青椒炒肉需要5秒; */
            Thread.sleep(5000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          pan.Cook(pan.steps_LaJiaoChaoRou);
        }
      }
    }.start();

    /* 线程2：老二炒番茄炒蛋。 */
    new Thread() {
      public void run() {
        /* 为了看出错乱效果，这里用死循环，一段时间后手工点击停止运行按钮 */
        while (true) {
          try {
            /* 模拟番茄炒蛋需要2秒; */
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          pan.Cook(pan.steps_FanQieChaoDan);
        }
      }
    }.start();
  }
}
