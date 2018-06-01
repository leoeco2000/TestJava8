package zuoxiaolong.interpreter;

import java.util.ArrayList;
import java.util.List;

// ������
public class Context {

  private int result;// ���

  private int index;// ��ǰλ��

  private int mark;// ��־λ

  private char[] inputChars;// ������ַ�����

  private List<Integer> operateNumbers = new ArrayList<Integer>(2);// ������

  private char operator;// �����

  public Context(char[] inputChars) {
    super();
    this.inputChars = inputChars;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public boolean hasNext() {
    return index != inputChars.length;
  }

  public char next() {
    return inputChars[index++];
  }

  public char current() {
    return inputChars[index];
  }

  public List<Integer> getOperateNumbers() {
    return operateNumbers;
  }

  public void setLeftOperateNumber(int operateNumber) {
    this.operateNumbers.add(0, operateNumber);
  }

  public void setRightOperateNumber(int operateNumber) {
    this.operateNumbers.add(1, operateNumber);
  }

  public char getOperator() {
    return operator;
  }

  public void setOperator(char operator) {
    this.operator = operator;
  }

  public void mark() {
    mark = index;
  }

  public void reset() {
    index = mark;
  }
}
