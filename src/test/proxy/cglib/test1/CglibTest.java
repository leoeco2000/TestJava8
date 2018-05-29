package test.proxy.cglib.test1;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {
  public static void main(String[] args) {
    MethodInterceptorImpl mImpl = new MethodInterceptorImpl();
    HelloWorld helloWorld = (HelloWorld) mImpl.createProxy(HelloWorld.class);
    helloWorld.sayHelloWorld();
  }
}


class HelloWorld {
  public void sayHelloWorld() {
    System.out.println("HelloWorld!");
  }
}


class MethodInterceptorImpl implements MethodInterceptor {

  public Object createProxy(Class<?> targetClass) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(targetClass);
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
      throws Throwable {
    System.out.println("Before invoke : " + method);
    Object result = proxy.invokeSuper(obj, args);
    System.out.println("After invoke : " + method);
    return result;
  }

}
