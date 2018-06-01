package yuanmadao.Interpreter.Impl.multi;

public class ClientMulti {
  public static void main(String[] args) throws Exception {
    // ׼��������
    Context c = new Context("data/InterpreterTest.xml");

    // ��Ҫ��ȡdԪ�ص�id���ԣ�Ҳ�������±��ʽ��ֵ��"a/b/d$.id$"
    // ����Ҫ�����������ĳ����﷨��
    ElementExpression root = new ElementExpression("root");
    ElementExpression aEle = new ElementExpression("a");
    ElementExpression bEle = new ElementExpression("b");
    ElementsExpression dEle = new ElementsExpression("d");
    PropertysTerminalExpression prop = new PropertysTerminalExpression("id");
    // ���
    root.addEle(aEle);
    aEle.addEle(bEle);
    bEle.addEle(dEle);
    dEle.addEle(prop);

    // ����
    String ss[] = root.interpret(c);
    for (String s : ss) {
      System.out.println("d������idֵ��=" + s);
    }
  }
}
