package zuoxiaolong.builder.withBuilder;

// �������
public class Creator {

  public static void main(String[] args) {
    Soul soul = new Soul();
    // ����һ����С�Ŀ���������Ա
    System.out.println("�����" + soul.createDoppelganger(new ThinBuilder("��������Ա1")));
    System.out.println("----------------------------------------");

    // ����һ�����ֵĿ���������Ա
    System.out.println("�����" + soul.createDoppelganger(new FatBuilder("��������Ա2")));
  }
}
