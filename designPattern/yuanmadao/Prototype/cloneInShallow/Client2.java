package yuanmadao.Prototype.cloneInShallow;

public class Client2 {
  public static void main(String[] args) {
    // �ȴ���ԭ��ʵ��
    PersonalOrder oa1 = new PersonalOrder();
    // ����ԭ��ʵ���Ķ���������ֵ
    oa1.setOrderProductNum(100);
    System.out.println("���ǵ�һ�λ�ȡ�Ķ���ʵ��===" + oa1.getOrderProductNum());
    // ͨ����¡����ȡ�µ�ʵ��
    PersonalOrder oa2 = (PersonalOrder) oa1.clone();
    oa2.setOrderProductNum(80);
    System.out.println("�����¡������ʵ��===" + oa2.getOrderProductNum());
    // �ٴ����ԭ��ʵ����ֵ
    System.out.println("�ٴ����ԭ��ʵ��===" + oa1.getOrderProductNum());
  }
}
