package yuanmadao.Prototype.simple;

/**
 * ��¡�ľ���ʵ�ֶ���
 */
public class ConcretePrototype1 implements Prototype {
  public Prototype clone() {
     //��򵥵Ŀ�¡���½�һ�������������û�����ԣ��Ͳ�ȥ����ֵ��
     Prototype prototype = new ConcretePrototype1();
     return prototype;
  }
}
