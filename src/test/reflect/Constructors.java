package test.reflect;

import java.lang.reflect.Constructor;

/*
 * ͨ��Class������Ի�ȡĳ�����еģ����췽������Ա��������Ա�����������ʳ�Ա��
 * 
 * 1.��ȡ���췽����
 * 
 * 1).�����ķ����� ����"���е�"���췽���� public Constructor[] getConstructors() ��ȡ���еĹ��췽��(����˽�С��ܱ�����Ĭ�ϡ�����)�� public
 * Constructor[] getDeclaredConstructors()
 * 
 * 2).��ȡ�����ķ����������ã�
 * 
 * ��ȡ������"���е�"���췽���� public Constructor getConstructor(Class... parameterTypes)
 * ��ȡ"ĳ�����췽��"������˽�еģ����ܱ�����Ĭ�ϡ����У� public Constructor getDeclaredConstructor(Class... parameterTypes):
 * 
 * ���ù��췽���� Constructor-->newInstance(Object... initargs)
 */
public class Constructors {

  public static void main(String[] args) throws Exception {
    // 1.����Class����
    Class clazz = Class.forName("test.reflect.Student");


    // 2.��ȡ���й��й��췽��
    System.out.println("***********���й��й��췽��***********");
    Constructor[] conArray = clazz.getConstructors();
    for (Constructor c : conArray) {
      System.out.println(c);
    }


    System.out.println("***********���еĹ��췽��(������˽�С��ܱ�����Ĭ�ϡ�����)***************");
    conArray = clazz.getDeclaredConstructors();
    for (Constructor c : conArray) {
      System.out.println(c);
    }

    System.out.println("***********��ȡ���С��޲εĹ��췽��************");
    Constructor con = clazz.getConstructor(null);
    // 1>����Ϊ���޲εĹ��췽������������һ��null,��дҲ���ԣ�������Ҫ����һ�����������ͣ��м�������
    // 2>�����ص�����������޲ι��캯���������

    System.out.println("con = " + con);
    // ���ù��췽��
    Object obj = con.newInstance();
    // System.out.println("obj = " + obj);
    // Student stu = (Student)obj;

    System.out.println("***********��ȡ˽�й��췽����������***********");
    con = clazz.getDeclaredConstructor(int.class);
    System.out.println(con);
    // ���ù��췽��
    con.setAccessible(true);// ��������(���Ե��������η�)
    obj = con.newInstance(666);
  }

}
