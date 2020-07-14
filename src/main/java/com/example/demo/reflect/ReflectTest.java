package com.example.demo.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/14
 * @version: 1.0
 */
public class ReflectTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InvocationTargetException,
      InstantiationException, NoSuchMethodException, NoSuchFieldException {
    /*================================================================*/
    Person person = new Person();
    System.out.println("包名: " + person.getClass().getPackage());
    System.out.println("类名: " + person.getClass().getName());
    System.out.println("简单名: " + person.getClass().getSimpleName());
    System.out.println("规范名: " + person.getClass().getCanonicalName());
    System.out.println("类型名: " + person.getClass().getTypeName());
    System.out.println("*================================================================*");
    /*================================================================*/

    Class<?> class1 = null;
    Class<?> class2 = null;
    Class<?> class3 = null;

    class1 = Class.forName("com.example.demo.reflect.Person");
    class2 = new Person().getClass();
    class3 = Person.class;

    System.out.println("类名: " + class1.getName());
    System.out.println("类名: " + class2.getName());
    System.out.println("类名: " + class3.getName());
    System.out.println("*================================================================*");
    /*================================================================*/

    Class<?> clazz = Class.forName("com.example.demo.reflect.Person");
    Class<?> superclass = clazz.getSuperclass();
    System.out.println("clazz的父类: " + superclass.getName());

    Class<?>[] interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      System.out.println("接口" + i + ": " + interfaces[i].getName());
    }
    System.out.println("*================================================================*");
    /*================================================================*/

    Constructor<?>[] constructors = clazz.getConstructors();
    System.out.println("构造方法共 " + constructors.length + "个");
    for (int i = 0; i < constructors.length; i++) {
      Class<?>[] parameterTypes = constructors[i].getParameterTypes();
      System.out.print("cons[" + i + "] (");
      for (int j = 0; j < parameterTypes.length; j++) {
        if (j == parameterTypes.length - 1) {
          System.out.print(parameterTypes[j].getName());
        } else {
          System.out.print(parameterTypes[j].getName() + ",");
        }
      }
      System.out.println(")");
    }
    /** 构造方法共 3个 cons[0] (java.lang.String,int) cons[1] (java.lang.String) cons[2] () */
    Person user1 =
        (Person)
            constructors[1].newInstance(
                "mazaiting", "mazaiting", "mazaiting", "mazaiting", "mazaiting");
    System.out.println(user1.toString());

    Person user2 = (Person) constructors[0].newInstance();
    System.out.println(user2.toString());
    System.out.println("*================================================================*");
    /*================================================================*/

    // 取得本类的全部属性
    Field[] fields = clazz.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      // 权限修饰
      int modifiers = fields[i].getModifiers();
      String mo = Modifier.toString(modifiers);
      // 属性类型
      Class<?> type = fields[i].getType();
      System.out.println(mo + " " + type.getSimpleName() + " " + fields[i].getName() + ";");
    }

    System.out.println("==========实现的接口或者父类的属性==========");

    // 取得实现的接口或者父类的属性
    Field[] fields2 = clazz.getFields();
    for (int i = 0; i < fields2.length; i++) {
      int modifiers = fields2[i].getModifiers();
      String mo = Modifier.toString(modifiers);

      Class<?> type = fields2[i].getType();
      System.out.println(mo + " " + type.getName() + " " + fields2[i].getName() + ";");
    }
    System.out.println("*================================================================*");
    /*================================================================*/
    // 获取与父类的全部方法，如果要获取当前类的方法所有方法
    // 则使用clazz.getDeclaredMethods()方法
    Method[] methods = clazz.getMethods();

    for (int i = 0; i < methods.length; i++) {
      // 返回类型
      Class<?> returnType = methods[i].getReturnType();
      int modifiers = methods[i].getModifiers();
      // 权限修饰符
      String modifier = Modifier.toString(modifiers);
      Class<?>[] parameterTypes = methods[i].getParameterTypes();
      Class<?>[] exceptionTypes = methods[i].getExceptionTypes();
      System.out.println("函数名: " + methods[i].getName());
      System.out.println("权限修饰: " + modifier);
      System.out.println("返回值: " + returnType.getName());
      System.out.println("参数个数: " + parameterTypes.length);
      System.out.println("异常个数: " + exceptionTypes.length);
      System.out.println("********************");

    }
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");

    // 获取当前类的reflect1方法
    Method reflect1 = clazz.getDeclaredMethod("toString");
    // 执行方法
    reflect1.invoke(clazz.getDeclaredConstructor().newInstance());

    Method reflect2 = clazz.getDeclaredMethod("toString");
    System.out.println(reflect2.invoke(clazz.getDeclaredConstructor(String.class,String.class,String.class,String.class,String.class).
        newInstance("mazaiting", "mazaiting", "mazaiting", "mazaiting", "mazaiting")));
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");
    // 得到当前类的实例
    Person instance = (Person) clazz.getDeclaredConstructor().newInstance();
    // 获取要更改的属性
    Field proprety = clazz.getDeclaredField("name11");
    // 设置可访问
    proprety.setAccessible(true);
    // 设置属性
    proprety.set(instance, "属性");
    System.out.println(proprety.get(instance));
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");
    ArrayList<Integer> list = new ArrayList<>();
    Method method = list.getClass().getDeclaredMethod("add", Object.class);
    method.invoke(list, "Java反射机制实例");
    System.out.println(list.get(0));
    /**
     * Java反射机制实例
     */
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");
    int[] temp = {1, 2, 3, 4, 5};
    Class<?> componentType = temp.getClass().getComponentType();
    System.out.println("数组类型: " + componentType.getName());
    System.out.println("数组长度: " + Array.getLength(temp));
    System.out.println("数组的第一个元素: " + Array.get(temp, 0));
    // 设置第一个元素数据为100
    Array.set(temp, 0, 100);
    System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");
    int[] newTemp = (int[]) arrayInc(temp, 15);
    print(newTemp);
    String[] atr = { "a", "b", "c" };
    String[] str1 = (String[]) arrayInc(atr, 8);
    print(str1);
    /**
     * 数组长度为： 15
     * 1 2 3 4 5 6 7 8 9 0 0 0 0 0 0
     * 数组长度为： 8
     * a b c null null null null null
     */
    System.out.println("---------------------------------------");
    System.out.println("*================================================================*");

  }
  // 修改数组大小
  public static Object arrayInc(Object obj, int len) {
    Class<?> arr = obj.getClass().getComponentType();
    Object newArr = Array.newInstance(arr, len);
    int co = Array.getLength(obj);
    System.arraycopy(obj, 0, newArr, 0, co);
    return newArr;
  }
  // 打印
  public static void print(Object obj) {
    Class<?> c = obj.getClass();
    if (!c.isArray()) {
      return;
    }
    System.out.println("数组长度为： " + Array.getLength(obj));
    for (int i = 0; i < Array.getLength(obj); i++) {
      System.out.print(Array.get(obj, i) + " ");
    }
    System.out.println();
  }
}
