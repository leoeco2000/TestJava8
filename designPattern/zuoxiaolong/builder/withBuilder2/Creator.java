package zuoxiaolong.builder.withBuilder2;

// �������
public class Creator {

  public static void main(String[] args) {
    // ����һ����С�Ŀ���������Ա
    System.out
        .println("�����" + new ThinBuilder().createDoppelganger(new DoppelgangerBuilder("��������Ա1")));
    System.out.println("----------------------------------------");

    // ����һ�����ֵĿ���������Ա
    System.out
        .println("�����" + new FatBuilder().createDoppelganger(new DoppelgangerBuilder("��������Ա2")));
  }
}
