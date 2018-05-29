package test.proxy.jdk.test0503;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

  public static void main(String[] args) {
    /*
     * LiuDeHuaProxy proxy = new LiuDeHuaProxy(); //获得代理对象 Person p = proxy.getProxy();
     * //调用代理对象的sing方法 String retValue = p.sing("冰雨"); System.out.println(retValue);
     * //调用代理对象的dance方法 String value = p.dance("江南style"); System.out.println(value);
     */


    // 加入这一段可以在磁盘中生成 代理类，让我们看到代理类的真面目
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
//    test.sing("冰雨");
  }
}
