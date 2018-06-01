package zuoxiaolong.interpreter;

// �������ʽ�����ս�����ʽ����Ӧarithmetic��
public class ArithmeticExpression implements Expression {

  public void interpreter(Context context) {
    context.setResult(getResult(context));// ������
    context.getOperateNumbers().clear();// ��ղ�����
    context.setLeftOperateNumber(context.getResult());// �����ѹ���������
  }

  private int getResult(Context context) {
    int result = 0;
    switch (context.getOperator()) {
      case '+':
        result = context.getOperateNumbers().get(0) + context.getOperateNumbers().get(1);
        break;
      case '-':
        result = context.getOperateNumbers().get(0) - context.getOperateNumbers().get(1);
        break;
      default:
        break;
    }
    return result;
  }

}
