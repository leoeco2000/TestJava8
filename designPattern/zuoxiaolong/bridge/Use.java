package zuoxiaolong.bridge;

public class Use {

  public static void main(String[] args) {
    Soul soul = new Doppelganger();
    soul.setAppearance(new TRH());// ���÷�����òΪ�߸�˧
    soul.setSkills(new Invisible());// ����������
    soul.doAllLikePeople();
    System.out.println("----------------------------");
    soul.show();
    soul.releaseSkills();
    System.out.println("----------------------------");
    // �л�����
    soul.setSkills(new ReadMind());
    soul.releaseSkills();
    System.out.println("----------------------------");
    // �л���ò
    soul.setAppearance(new Loser());
    soul.show();
    System.out.println("----------------------------");
    // �л�����
    soul.setSkills(new Volant());
    soul.releaseSkills();
  }

}
