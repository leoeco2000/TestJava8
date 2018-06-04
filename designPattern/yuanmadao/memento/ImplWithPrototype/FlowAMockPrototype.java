package yuanmadao.memento.ImplWithPrototype;

/**
 * ģ����������A��ֻ��һ��ʾ�⣬��ָĳ����������
 */
public class FlowAMockPrototype implements Cloneable {
  private String flowName;
  private int tempResult;
  private String tempState;

  public FlowAMockPrototype(String flowName) {
    this.flowName = flowName;
  }

  public void runPhaseOne() {
    // ������׶Σ����ܲ������м�����ʾ��һ��
    tempResult = 3;
    tempState = "PhaseOne";
  }

  public void schema1() {
    // ʾ�⣬��Ҫʹ�õ�һ���׶β���������
    this.tempState += ",Schema1";
    System.out.println(this.tempState + " : now run " + tempResult);
    this.tempResult += 11;
  }

  public void schema2() {
    // ʾ�⣬��Ҫʹ�õ�һ���׶β���������
    this.tempState += ",Schema2";
    System.out.println(this.tempState + " : now run " + tempResult);
    this.tempResult += 22;
  }

  /**
   * ��������ԭ���������״̬�ı���¼����
   * 
   * @return �����õı���¼����
   */
  public FlowAMockMemento createMemento() {
    try {
      return new MementoImplPrototype((FlowAMockPrototype) this.clone());
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * ��������ԭ���������״̬������ص�����¼�����¼��״̬
   * 
   * @param memento ��¼��ԭ����״̬�ı���¼����
   */
  public void setMemento(FlowAMockMemento memento) {
    MementoImplPrototype mementoImpl = (MementoImplPrototype) memento;
    this.tempResult = mementoImpl.getFlowAMock().tempResult;
    this.tempState = mementoImpl.getFlowAMock().tempState;
  }

  /**
   * �����ı���¼����ʵ�ֱ���¼խ�ӿڣ�ʵ�ֳ�˽�е��ڲ��࣬�����ⲿ����
   */
  private static class MementoImplPrototype implements FlowAMockMemento {
    private FlowAMockPrototype flowAMock = null;

    public MementoImplPrototype(FlowAMockPrototype f) {
      this.flowAMock = f;
    }

    public FlowAMockPrototype getFlowAMock() {
      return flowAMock;
    }
  }
}
