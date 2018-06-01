package yuanmadao.Interpreter.Impl;

public class Client {
  public static void main(String[] args) throws Exception {
    // ׼��������
    Context c = new Context("data/InterpreterTest.xml");

    // ��Ҫ��ȡcԪ�ص�ֵ��Ҳ�������±��ʽ��ֵ��"root/a/b/c"
    // ����Ҫ�����������ĳ����﷨��
    ElementExpression root = new ElementExpression("root");
    ElementExpression aEle = new ElementExpression("a");
    ElementExpression bEle = new ElementExpression("b");
    ElementTerminalExpression cEle = new ElementTerminalExpression("c");
    // �������
    root.addEle(aEle);
    aEle.addEle(bEle);
    bEle.addEle(cEle);
    // ����
    String ss[] = root.interpret(c);
    System.out.println("c��ֵ��=" + ss[0]);
  }
}

