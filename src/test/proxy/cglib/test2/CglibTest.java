package test.proxy.cglib.test2;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {
  public static void main(String[] args) {

    BusinessInterfaceProxy proxy = new BusinessInterfaceProxy();
    BusinessObject bProxy = (BusinessObject) proxy.getInstance(new BusinessObject());
    System.out.println(bProxy.getClass().getName());
    System.out.println(bProxy.getClass().getSuperclass().getName());
    bProxy.doSomething();
  }
}

class BusinessObject {

  public void doSomething() {
    System.out.println("方法正在执行...");
  }
}

class BusinessInterfaceProxy implements MethodInterceptor {

//  private Object target;

  public Object getInstance(Object target) {

//    this.target = target;
    Enhancer enhancer = new Enhancer();

//    enhancer.setSuperclass(this.target.getClass());
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback(this);

    return enhancer.create();
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
      throws Throwable {

    System.out.println("事物开始...");
    proxy.invokeSuper(obj, args);
    System.out.println("事物结束...");
    return null;
  }

}
