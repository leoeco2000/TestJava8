package zuoxiaolong.bridge;

// ��ò�ӿ�
public interface Appearance {

  public void show();

}

// ��˿����
class Loser implements Appearance {

  public void show() {
    System.out.println("չʾ��˿����");
  }

}

// �߸�˧����
class TRH implements Appearance {

  public void show() {
    System.out.println("չʾ�߸�˧����");
  }

}
