package zuoxiaolong.interpreter;

// ���ս�����ʽ����Ӧnumber
public class NumberExpression implements Expression {

  public void interpreter(Context context) {
    // ���ò�����
    Integer operateNumber = Integer.valueOf(String.valueOf(context.current()));
    if (context.getOperateNumbers().size() == 0) {
      context.setLeftOperateNumber(operateNumber);
      context.setResult(operateNumber);
    } else {
      context.setRightOperateNumber(operateNumber);
      Expression expression = new ArithmeticExpression();// ת�����������ʽ
      expression.interpreter(context);
    }
  }

}
