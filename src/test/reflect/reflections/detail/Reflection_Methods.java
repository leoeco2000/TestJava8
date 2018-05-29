package test.reflect.reflections.detail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Fant.J.
 * 2018/2/7 15:04
 */
public class Reflection_Methods {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //获取�?有的共有方法
        Class aClass = People.class;
        Method [] methods = aClass.getMethods();

        //获取知道方法名称和参�? 的方�?,   如果没有参数，则传入null
        Method method = aClass.getMethod("setName", String.class);
        Method method1 = aClass.getMethod("getName",null);

        //根据method获取参数类型
        method.getParameterTypes();
        //根据method获取返回值类�?
        method.getReturnType();

        /**
         *  使用反射来调用方法�?�如果方法是静�?�方法，则不�?要实例该对象�?
         *  因为我这里这个方法不是静态的。所以我实例化People对象
         *  仔细看看method和method1 是啥。代表了�?
         */
        People people = new People();
            method.invoke(people,"Fant.J");
            Object obj = method1.invoke(people,null);
            System.out.println(obj);
    }
}
