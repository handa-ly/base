package com.example.demo.designMode.flyWeight;

import java.util.HashMap;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 16:55
 * @Version:1.0
 * @Description:
 */
public class FlyweightFactory {
    //定义一个HashMap用于存储享元对象，实现享元池
    private HashMap flyweights = new HashMap();
    public Flyweight getFlyweight(String key){
        //如果对象存在，则直接从享元池获取
        if(flyweights.containsKey(key)){
            return(Flyweight)flyweights.get(key);
        }
        //如果对象不存在，先创建一个新的对象添加到享元池中，然后返回
        else {
            Flyweight fw = new ConcreteFlyweight("null");
            flyweights.put(key,fw);
            return fw;
        }
    }
}