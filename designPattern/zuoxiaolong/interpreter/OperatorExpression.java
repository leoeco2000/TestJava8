package zuoxiaolong.interpreter;

// �ս�����ʽ����Ӧ-��+
public class OperatorExpression implements Expression {

  public void interpreter(Context context) {
    context.setOperator(context.current());// ���������
  }

}
