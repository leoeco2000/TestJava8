package test.reflect;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ClassInfo {
  public static void main(String[] args)
      throws ReflectiveOperationException, IllegalAccessException, Throwable, Exception {
    Class clazz = Class.forName("test.reflect.Student");
    Constructor con = clazz.getConstructor(null);
    Object obj = con.newInstance();
    // Student obj = new Student();

    Class stuInstance = obj.getClass();
    Class stu = Student.class;

    // stu.getName();
    // int modifiers = stu.getModifiers();

    clazz.getName();
    int modifiers = stu.getModifiers();

    Modifier.isAbstract(modifiers);
    Modifier.isFinal(modifiers);
    Modifier.isInterface(modifiers);
    Modifier.isNative(modifiers);
    Modifier.isPrivate(modifiers);
    Modifier.isProtected(modifiers);
    Modifier.isPublic(modifiers);
    Modifier.isStatic(modifiers);
    Modifier.isStrict(modifiers);
    Modifier.isSynchronized(modifiers);
    Modifier.isTransient(modifiers);
    Modifier.isVolatile(modifiers);

    Package mypackage = clazz.getPackage();

    Class superclass = clazz.getSuperclass();

    Class[] interfaces = clazz.getInterfaces();
    AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
    Type[] genericInterfaces = clazz.getGenericInterfaces();

  }
}
