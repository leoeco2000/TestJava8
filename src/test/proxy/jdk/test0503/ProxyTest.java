package test.proxy.jdk.test0503;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

  public static void main(String[] args) {
    /*
     * LiuDeHuaProxy proxy = new LiuDeHuaProxy(); //��ô������ Person p = proxy.getProxy();
     * //���ô�������sing���� String retValue = p.sing("����"); System.out.println(retValue);
     * //���ô�������dance���� String value = p.dance("����style"); System.out.println(value);
     */


    // ������һ�ο����ڴ��������� �����࣬�����ǿ��������������Ŀ
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    LiuDeHuaProxy source = new LiuDeHuaProxy();
    source.getProxy().sing("a");
//    Person test = (Person) Proxy.newProxyInstance(
//        Thread.currentThread().getContextClassLoader(),
//        new Class[] {Person.class},
//        new InvocationHandler() {
//          @Override
//          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            if ("method".equals(method.getName())) {
//              return method.invoke(source, args);
//            }
//            return null;
//          }
//        });
//    test.sing("����");
  }
}
