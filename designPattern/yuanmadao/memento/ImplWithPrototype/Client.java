package yuanmadao.memento.ImplWithPrototype;

public class Client {
  public static void main(String[] args) {
    // ����ģ���������̵Ķ���
    FlowAMockPrototype mock = new FlowAMockPrototype("TestFlow");
    // �������̵ĵ�һ���׶�
    mock.runPhaseOne();
    // ����һ��������
    FlowAMementoCareTaker careTaker = new FlowAMementoCareTaker();
    // ������ʱ����ı���¼���󣬲����浽�����߶����������Ҫ��
    FlowAMockMemento memento = mock.createMemento();
    careTaker.saveMemento(memento);

    // ���շ���һ���������̺�벿��
    mock.schema1();

    // �ӹ����߻�ȡ����¼����Ȼ�����û�ȥ��
    // ��ģ���������̵Ķ����Լ��ָ��Լ����ڲ�״̬
    mock.setMemento(careTaker.retriveMemento());

    // ���շ��������������̺�벿��
    mock.schema2();
  }
}
