package zuoxiaolong.interpreter;

import java.util.ArrayList;
import java.util.List;


public class Client {

  public static void main(String[] args) {
    List<String> inputList = new ArrayList<String>();
    // ������ȷ�ģ����������
    inputList.add("1+2+3+4+5+6+7+8+9");
//    inputList.add("1-2+3-4+5-6+7-8+9");
//    inputList.add("9");
//    inputList.add("-1+2+3+5");
//    inputList.add("1*2");
//    inputList.add("11+2+3+9");

    GrammarParser grammarParser = new GrammarParser();// �﷨������
    
//    for (char c : inputList.get(0).toCharArray()) {
//      System.out.println(c);
//    }

    for (String input : inputList) {
      Context context = new Context(input.toCharArray());
      try {
        grammarParser.parse(context);// �﷨����������ý��������ͱ��ʽ
        System.out.println(input + "=" + context.getResult());
      } catch (Exception e) {
        System.out.println("�﷨������������ȷ�ı��ʽ��");
      }
    }
    
    System.out.println(Integer.valueOf(String.valueOf("+")));
  }

}
