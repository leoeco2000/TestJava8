package test.proxy.jdk.test0503;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: LiuDeHuaProxy
 * @Description: ��������ฺ���������»��Ĵ�����
 * @author: �°�����
 * @date: 2014-9-14 ����9:50:02
 *
 */
public class LiuDeHuaProxy {

  // ���һ���������ס������Ҫ�����Ŀ�����
  private Person ldh = new LiuDeHua();

  /**
   * ���һ���������ɴ������
   * 
   * @Method: getProxy
   * @Description: ��������������»��Ĵ������Person person = LiuDeHuaProxy.getProxy();//�õ�һ���������
   * @Anthor:�°�����
   *
   * @return ĳ������Ĵ������
   */
  public Person getProxy() {

    System.out.println(ldh.getClass());
    // ʹ��Proxy.newProxyInstance(
    //   ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
    // ����ĳ������Ĵ������
    return (Person) Proxy.newProxyInstance(
        ldh.getClass().getClassLoader(),
        ldh.getClass().getInterfaces(),
        new InvocationHandler() {
          /**
           * InvocationHandler�ӿ�ֻ������һ��invoke������
           * ��˶��������Ľӿڣ����ǲ��õ���ȥ����һ������ʵ�ָýӿڣ�
           * ����ֱ��ʹ��һ�������ڲ�����ʵ�ָýӿڣ�
           * new InvocationHandler() {}
           * �������InvocationHandler�ӿڵ�����ʵ����
           * 
           * ��invoke��������ָ�����صĴ������ɵĹ���
           * proxy : �Ѵ�������Լ����ݽ���
           * method ���Ѵ������ǰ���õķ������ݽ���
           * args : �ѷ����������ݽ���
           * 
           * �����ô�������person.sing("����");
           * ���� person.dance("����style");����ʱ��
           * ʵ����ִ�еĶ���invoke��������Ĵ��룬
           * ������ǿ�����invoke������ʹ��method.getName()
           * �Ϳ���֪����ǰ���õ��Ǵ��������ĸ�����
           */
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // ������õ��Ǵ�������sing����
            if (method.getName().equals("sing")) {
              System.out.println("�������ľ����ˣ�Ҫ����������ȸ�ʮ���Ǯ����");
              // �Ѿ���Ǯ�ˣ��������Լ����ᳪ�裬��ֻ�������»�ȥ���裡
              return method.invoke(ldh, args); // ������������ʵĿ������sing����ȥ�����û�����
            }
            // ������õ��Ǵ�������dance����
            if (method.getName().equals("dance")) {
              System.out.println("�������ľ����ˣ�Ҫ����������ȸ���ʮ���Ǯ����");
              // �Ѿ���Ǯ�ˣ��������Լ����ᳪ�裬��ֻ�������»�ȥ���裡
              return method.invoke(ldh, args);// ������������ʵĿ������dance����ȥ�����û�����
            }

            return null;
          }
        });
  }
}
