package test.reflect.reflections.detail;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Fant.J.
 * 2018/2/7 13:28
 */
public class Reflection_Contructor {
    public static void main(String[] args) throws Exception {

        //获取People类对�?. 方法�?
        Class<People> aClass = People.class;

        //方法�?
        //Class aClass = Class.forName("com.reflection.detail.People");

        //获取构�?�器信息
        Constructor[] constructors = aClass.getConstructors();

        //使用构�?�器对象实例化对�?
        Constructor constructor = aClass.getConstructor(Integer.class,String.class);
        People people = (People) constructor.newInstance(1,"FantJ");

        //你也可以获取这个构�?�方法的参数,并打�?
        Class[] paramTypes =  constructor.getParameterTypes();
        Arrays.stream(paramTypes).forEach(p-> System.out.println(p));
    }
}
