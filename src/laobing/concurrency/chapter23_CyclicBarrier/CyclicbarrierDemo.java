package laobing.concurrency.chapter23_CyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicbarrierDemo {

  public static void main(String[] args) {

    /* Ԥ������ĳ��� */
    final CyclicBarrier cbr = new CyclicBarrier(5);
    List<Team> teams = new ArrayList<Team>();
    teams.add(new Team("������",cbr));
    teams.add(new Team("����",cbr));
    teams.add(new Team("��ŵ",cbr));
    teams.add(new Team("��ţ",cbr));
    teams.add(new Team("÷����˹",cbr));
    /* Ϊÿ�����ӿ���һ���߳� */
    for (Team team : teams) {
//      final Team teamName = team;
      new Thread(team).start();
    }
  }
  

  /*ά�޼�ʦ��*/
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
          System.out.println("��" + cbr.getParties() + "����������" + teamName + "���ӵ�"
              + (cbr.getNumberWaiting() + 1) + "��׼������");
          /* �ȴ�5�γ��������󣬲����������� */

          // ���⣬ǧ��Ҫ��ͼд��������ͬ�����룺
          // synchronized (this) {
          // System.out.println("��"+cbr.getParties()+"����������"+tempteam+"���ӵ�"+(cbr.getNumberWaiting()+1)+"��׼������");
          // cbr.await();
          // }
          // ��await()����ͬ��������lock����
          // �����ͻ����߳���������Ϊawait()�ȴ���ͬ���������޷�ִ���꣬�����߳̾��޷����д�����е���wait�ȴ�����ô��Զ�����ܴﵽ���ϵ㣬�����߳����С�
          cbr.await();
          // �鿴���������߳��Ƿ��ж�
//          System.out.println(cbr.isBroken());
          System.out.println(teamName + "���ӳ���");
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      }
  }
}
