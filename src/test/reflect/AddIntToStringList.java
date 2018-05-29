package test.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * ͨ������Խ�����ͼ��
 * 
 * ���磺��һ��String���͵ļ��ϣ���������������������һ��Integer���͵�ֵ��
 */
public class AddIntToStringList {
  public static void main(String[] args) throws Exception {
    List<String> strList = new ArrayList<>();
    strList.add("aaa");
    strList.add("bbb");

    // strList.add(100);
    // ��ȡArrayList��Class���󣬷���ĵ���add()�������������
    Class listClass = strList.getClass(); // �õ� strList ������ֽ��� ����
    // ��ȡadd()����
    Method m = listClass.getMethod("add", Object.class);
    // ����add()����
    m.invoke(strList, 100);

    // ��������
    for (Object obj : strList) {
      System.out.println(obj);
    }
  }
}
