package test.reflect.reflections.detail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Fant.J.
 * 2018/2/7 12:28
 */
public class Reflection_Class {

    public static void main(String[] args) throws NoSuchMethodException {
        Class myObjectClass = MyObjectClass.class;
        //获取类信�?
        String className = myObjectClass.getName();
        String classSimpleName = myObjectClass.getSimpleName();
        /**
         * 获取修饰符信�?
         *   Modifier.isAbstract(int modifiers)
             Modifier.isFinal(int modifiers)
             Modifier.isInterface(int modifiers)
             Modifier.isNative(int modifiers)
             Modifier.isPrivate(int modifiers)
             Modifier.isProtected(int modifiers)
             Modifier.isPublic(int modifiers)
             Modifier.isStatic(int modifiers)
             Modifier.isStrict(int modifiers)
             Modifier.isSynchronized(int modifiers)
             Modifier.isTransient(int modifiers)
             Modifier.isVolatile(int modifiers)
         */
        int modifiers = myObjectClass.getModifiers();

        //获取包信�?
        Package packageInfo  = myObjectClass.getPackage();

        //获取父类信息
        Class superClass = myObjectClass.getSuperclass();

        //获取实现的接�?
        Class [] interfaces = myObjectClass.getInterfaces();

        //获取构�?�函数信�?
        Constructor[] constructors = myObjectClass.getConstructors();

        //获取方法信息
        Method[] methods = myObjectClass.getMethods();

        //获取字段信息
        Field[] fields = myObjectClass.getFields();

        //获取注解信息
        Annotation [] annotations = myObjectClass.getAnnotations();

    }


    class MyObjectClass {

    }
}
