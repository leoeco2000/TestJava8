package zuoxiaolong.interpreter;

// �ս�����ʽ����Ӧ0��1��2��3��4��5��6��7��8��9
public class DigitExpression implements Expression {

  public void interpreter(Context context) {
    Expression expression = new NumberExpression();// ��������֣���ֱ��תΪnumber���ʽ
    expression.interpreter(context);
  }

}
