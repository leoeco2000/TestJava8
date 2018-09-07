package temp;

import java.lang.reflect.Field;


public class foreachTest {
  public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
     stringtest();
  }

  static void stringtest() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
      IllegalAccessException {
    // �����ַ���"Hello World"�� ����������s
    String s = "Hello World";
    System.out.println("s = " + s); // Hello World

    // ��ȡString���е�value�ֶ�
    Field valueFieldOfString = String.class.getDeclaredField("value");
    // �ı�value���Եķ���Ȩ��
    valueFieldOfString.setAccessible(true);

    // ��ȡs�����ϵ�value���Ե�ֵ
    char[] value = (char[]) valueFieldOfString.get(s);
    // �ı�value�����õ������еĵ�5���ַ�
    value[5] = '_';
    System.out.println("s = " + s); // Hello_World
  }

}


class Demo {
  private String name;

  Demo(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}

