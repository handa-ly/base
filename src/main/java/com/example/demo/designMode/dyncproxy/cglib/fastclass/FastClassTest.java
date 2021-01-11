package com.example.demo.designMode.dyncproxy.cglib.fastclass;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.reflect.FastClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: hanDa
 * @Date: 2020/12/16 21:22
 * @Version:1.0
 * @Description:
 */
public class FastClassTest {
    public class DelegateClass {

        public DelegateClass() {
        }

        public DelegateClass(String string) {
        }

        public boolean add(String string, int i) {
            System.out.println("This is add method: " + string + ", " + i);
            return true;
        }

        public void update() {
            System.out.println("This is update method");
        }
    }

    public static void main(String[] args) throws Exception {
        FastClassTest fastClassTest = new FastClassTest();
        // 保留生成的FastClass类文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\handa_ly\\Desktop\\FastClass");

        Class delegateClass = DelegateClass.class;

        // Java Reflect

        // 反射构造类
        Constructor delegateConstructor = delegateClass.getConstructor(fastClassTest.getClass(),String.class);
        // 创建委托类实例
        DelegateClass delegateInstance = (DelegateClass) delegateConstructor.newInstance(fastClassTest,"Tom");

        // 反射方法类
        Method addMethod = delegateClass.getMethod("add", String.class, int.class);
        // 调用方法
        addMethod.invoke(delegateInstance, "Tom", 30);

        Method updateMethod = delegateClass.getMethod("update");
        updateMethod.invoke(delegateInstance);

        // CGLib FastClass

        // FastClass动态子类实例
        FastClass fastClass = FastClass.create(DelegateClass.class);

        // 创建委托类实例，调用构造器
        DelegateClass fastInstance = (DelegateClass) fastClass.newInstance(
                new Class[] {FastClassTest.class,String.class}, new Object[]{fastClassTest,"Jack"});

        // 调用委托类方法
        fastClass.invoke("add", new Class[]{ String.class, int.class}, fastInstance,
                new Object[]{ "Jack", 25});


    }
}