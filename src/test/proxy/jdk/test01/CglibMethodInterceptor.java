package test.proxy.jdk.test01;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterceptor implements MethodInterceptor {
  @Override
  public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("------before " + methodProxy.getSuperName() + "------");
    Object result = methodProxy.invokeSuper(target, args);
    System.out.println("------after " + methodProxy.getSuperName() + "------");
    return result;
  }

  public Object getInstance(Object target) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }

  
  public static void main(String[] args) {
    CglibMethodInterceptor cglibProxy = new CglibMethodInterceptor();

//    Enhancer enhancer = new Enhancer();
//    enhancer.setSuperclass(UserServiceImpl.class);
//    enhancer.setCallback(cglibProxy);

//    UserService o = (UserService) enhancer.create();
    UserService userProxy = (UserService) cglibProxy.getInstance(new UserServiceImpl());
    userProxy.getName(1);
    userProxy.getAge(1);
  }
}

