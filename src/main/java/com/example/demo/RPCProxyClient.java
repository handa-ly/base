package com.example.demo;

import java.lang.reflect.Method;

public class RPCProxyClient implements java.lang.reflect.InvocationHandler {
    private Object obj;

    public RPCProxyClient(Object obj) {
        this.obj = obj;
    }

    /**
     * 得到被代理对象;
     */
    public static Object getProxy(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new RPCProxyClient(obj));
    }

    /**
     * 调用此方法执行
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //结果参数;
        Object result = new Object();
        // ...执行通信相关逻辑
        // ...
        return result.toString();
    }

    public interface HelloWorldService {
        String sayHello(String msg);
    }

    public static class HelloWorldServiceImpl implements HelloWorldService {
        @Override
        public String sayHello(String msg) {
            String result = "hello world " + msg;
            System.out.println(result);
            return result;
        }
    }


    public static void main(String[] args) {
        HelloWorldService helloWorldService = (HelloWorldService) RPCProxyClient.getProxy(new HelloWorldServiceImpl());
        helloWorldService.sayHello("test");
    }

}