package zuoxiaolong.bridge.wrong;

// ���������
public abstract class Soul {

  public void createDoppelganger() {
    System.out.println("��������");
  }

}

// ��˿����
class Loser extends Soul {

  public Loser() {
    System.out.println("����һ���޼��ܵČ�˿����");
  }

  public void doAllLikePeople() {
    System.out.println("���������������һ�����κ���");
  }

  public void show() {
    System.out.println("չʾ��˿����");
  }

}

// �߸�˧����
class TRH extends Soul {

  public TRH() {
    System.out.println("����һ���޼��ܵĸ߸�˧����");
  }

  public void doAllLikePeople() {
    System.out.println("���������������һ�����κ���");
  }

  public void show() {
    System.out.println("չʾ�߸�˧����");
  }

}

// ���������ܵČ�˿����
class InvisibleLoser extends Loser {

  public InvisibleLoser() {
    System.out.println("����һ���������ܵČ�˿����");
  }

  public void releaseSkills() {
    System.out.println("��˿�����ͷ�������");
  }
}

// ���з��м��ܵČ�˿����
class VolantLoser extends Loser {

  public VolantLoser() {
    System.out.println("����һ�������м��ܵČ�˿����");
  }

  public void releaseSkills() {
    System.out.println("��˿�����ͷŷ��м���");
  }
}

// ���ж��������ܵČ�˿����
class ReadMindLoser extends Loser {

  public ReadMindLoser() {
    System.out.println("����һ�������������ܵČ�˿����");
  }

  public void releaseSkills() {
    System.out.println("��˿�����ͷŶ���������");
  }
}

// ���������ܵĸ߸�˧����
class InvisibleTRH extends TRH {

  public InvisibleTRH() {
    System.out.println("����һ���������ܵĸ߸�˧����");
  }

  public void releaseSkills() {
    System.out.println("�߸�˧�����ͷ�������");
  }
}

// ���з��м��ܵĸ߸�˧����
class VolantTRH extends TRH {

  public VolantTRH() {
    System.out.println("����һ�������м��ܵĸ߸�˧����");
  }

  public void releaseSkills() {
    System.out.println("�߸�˧�����ͷŷ��м���");
  }
}

// ���ж��������ܵĸ߸�˧����
class ReadMindTRH extends TRH {

  public ReadMindTRH() {
    System.out.println("����һ�������������ܵĸ߸�˧����");
  }

  public void releaseSkills() {
    System.out.println("�߸�˧�����ͷŶ���������");
  }
}
