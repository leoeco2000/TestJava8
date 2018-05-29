package test.proxy.jdk.test01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserInvocationHandler implements InvocationHandler {
  private Object target;

  UserInvocationHandler() {
    super();
  }

  UserInvocationHandler(Object target) {
    super();
    this.target = target;
  }

  public Object getInstance() {
    UserService userServiceProxy =
        (UserService) Proxy.newProxyInstance(
            this.target.getClass().getClassLoader(),
            this.target.getClass().getInterfaces(), this);
    return userServiceProxy;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if ("getName".equals(method.getName())) {
      System.out.println("++++++before " + method.getName() + "++++++");
      Object result = method.invoke(target, args);
      System.out.println("++++++after " + method.getName() + "++++++");
      return result;
    } else {
      Object result = method.invoke(target, args);
      return result;
    }

  }


  public static void main(String[] args) {
    UserService userService = new UserServiceImpl();
    UserInvocationHandler invocationHandler = new UserInvocationHandler(userService);

    UserService userServiceProxy = 
        (UserService) invocationHandler.getInstance();
//    UserService userServiceProxy =
//        (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
//            userService.getClass().getInterfaces(), invocationHandler);

    System.out.println(userServiceProxy.getName(1));
    System.out.println(userServiceProxy.getAge(1));
  }

}
