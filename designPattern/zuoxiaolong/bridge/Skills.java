package zuoxiaolong.bridge;

// ���ܽӿ�
public interface Skills {

  public void releaseSkills();

}

// ������
class Invisible implements Skills {

  public void releaseSkills() {
    System.out.println("�ͷ�������");
  }
}

// ���м���
class Volant implements Skills {

  public void releaseSkills() {
    System.out.println("�ͷŷ��м���");
  }
}

// ����������
class ReadMind implements Skills {

  public void releaseSkills() {
    System.out.println("�ͷŶ���������");
  }
}
