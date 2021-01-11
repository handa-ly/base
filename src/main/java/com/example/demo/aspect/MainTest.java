package com.example.demo.aspect;

import com.example.demo.aspect.target.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
    *@ClassName: MainTest
    *@Description: TODO 基于ProxyFactoryBean代理实现：
 *                      demo1: 目标类的advice，类层
 *                      demo2: Advisor指定切入点，细颗粒到方法层
 *                      demo3: 只想拦截在某种情况下调用的方法
 *                             -例如：ControlFlowPointcut 第一个构造函数参数就是拦截的目标类，第二个构造函数参数就是要拦截的方法名
 *                      demo4:利用aspects 的aopxml配置
    *@Author: handa
    *@Date: 2020/4/23 11:55
    */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext1.xml");
//                "applicationContext2.xml");
//                "applicationContext3.xml");
                "applicationContext4.xml");
        IUserService userService = (IUserService) context.getBean("myProxy");

        String addSuccess = userService.addUser("ton", 56);
        String deleteSuccess = userService.deleteUser("ton");
        aaMethod(userService);
    }

    public static void aaMethod(IUserService userService){
        userService.addUser("ton2", 56);//被拦截
        userService.deleteUser("Alex");//被拦截
    }
}
