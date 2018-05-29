package test.reflect.reflections.detail;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 反射泛型 Created by Fant.J. 2018/2/7 16:31
 */
public class Reflection_Generics {
  public static void main(String[] args) throws NoSuchMethodException {

    Class aClass = People.class;
    Method getStringList = aClass.getMethod("getStringList", null);
    // 获取方法的返回值类型
    Type returnType = getStringList.getGenericReturnType();
    System.out.println(returnType);

    if (returnType instanceof ParameterizedType) {
      ParameterizedType type = (ParameterizedType) returnType;
      Type[] typeArguments = type.getActualTypeArguments();
      for (Type typeArgument : typeArguments) {
        Class typeArgClass = (Class) typeArgument;
        System.out.println("typeArgClass = " + typeArgClass);
      }
    }

    Method method = MyClass.class.getMethod("setStringList", List.class);

    Type[] genericParameterTypes = method.getGenericParameterTypes();

    for (Type genericParameterType : genericParameterTypes) {
      if (genericParameterType instanceof ParameterizedType) {
        ParameterizedType aType = (ParameterizedType) genericParameterType;
        System.out.println("ParameterizedType = " + aType);
        Type[] parameterArgTypes = aType.getActualTypeArguments();
        for (Type parameterArgType : parameterArgTypes) {
          Class parameterArgClass = (Class) parameterArgType;
          System.out.println("parameterArgClass = " + parameterArgClass);
        }
      }
    }
  }
}
