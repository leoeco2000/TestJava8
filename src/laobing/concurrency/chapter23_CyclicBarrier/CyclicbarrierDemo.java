package laobing.concurrency.chapter23_CyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicbarrierDemo {

  public static void main(String[] args) {

    /* 预设参赛的车队 */
    final CyclicBarrier cbr = new CyclicBarrier(5);
    List<Team> teams = new ArrayList<Team>();
    teams.add(new Team("法拉利",cbr));
    teams.add(new Team("麦凯伦",cbr));
    teams.add(new Team("雷诺",cbr));
    teams.add(new Team("红牛",cbr));
    teams.add(new Team("梅赛德斯",cbr));
    /* 为每个车队开启一个线程 */
    for (Team team : teams) {
//      final Team teamName = team;
      new Thread(team).start();
    }
  }
  

  /*维修技师类*/
  static class Team implements Runnable{
      String teamName;
      CyclicBarrier cbr;
      public Team(String teamName,CyclicBarrier cbr) {
          this.teamName = teamName;
          this.cbr = cbr;
      }
      @Override
      public void run() {
        try {
          Thread.sleep(new Random().nextInt(6));
          System.out.println("共" + cbr.getParties() + "辆车参赛，" + teamName + "车队第"
              + (cbr.getNumberWaiting() + 1) + "个准备就绪");
          /* 等待5参车都就绪后，才能往下运行 */

          // 另外，千万不要试图写出这样的同步代码：
          // synchronized (this) {
          // System.out.println("共"+cbr.getParties()+"辆车参赛，"+tempteam+"车队第"+(cbr.getNumberWaiting()+1)+"个准备就绪");
          // cbr.await();
          // }
          // 将await()放在同步代码块或lock锁中
          // 这样就会让线程死锁，因为await()等待，同步代码块就无法执行完，其它线程就无法进行代码块中调用wait等待，那么永远不可能达到屏障点，而让线程运行。
          cbr.await();
          // 查看被阻塞的线程是否被中断
//          System.out.println(cbr.isBroken());
          System.out.println(teamName + "车队出发");
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      }
  }
}
