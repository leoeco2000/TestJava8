package zuoxiaolong.interpreter;

// �﷨��������������ս�����ģʽ����ƣ���Щ����Ӧ�����ڿͻ��ˣ�Ϊ�˸����������������һ���﷨��������
public class GrammarParser {

  // �﷨����
  public void parse(Context context) throws Exception {
    while (context.hasNext()) {
      Expression expression = null;
      switch (context.current()) {
        case '+':
        case '-':
          checkGrammar(context);
          expression = new OperatorExpression();
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          context.mark();
          checkGrammar(context, context.current());
          context.reset();
          expression = new DigitExpression();
          break;
        default:
          throw new RuntimeException("�﷨����");// ��Ч����
      }
      expression.interpreter(context);
      context.next();
    }
  }

  // ����﷨
  private void checkGrammar(Context context, char current) {
    context.next();
    if (context.hasNext() && context.current() != '+' && context.current() != '-') {
      throw new RuntimeException("�﷨����");// ��5��
    }
    try {
      Integer.valueOf(String.valueOf(current));
    } catch (Exception e) {
      throw new RuntimeException("�﷨����");// ��6��
    }
  }

  // ����﷨
  private void checkGrammar(Context context) {
    if (context.getOperateNumbers().size() == 0) {// ��4��
      throw new RuntimeException("�﷨����");
    }
    if (context.current() != '+' && context.current() != '-') {// ��7��
      throw new RuntimeException("�﷨����");
    }
  }

}
