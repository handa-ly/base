package com.example.demo.designMode.bridgePattern;

/**
 * @Author: hanDa
 * @Date: 2021/1/27 10:13
 * @Version:1.0
 * @Description:
 */
public class RefinedAbstraction extends Abstraction{
    //覆写构造函数
    public RefinedAbstraction(Implementor _imp){
        super(_imp);
    }
    //修正父类的行为
    @Override
    public void request(){
        /*
         * 业务处理...
         */
        super.request();
        super.getImp().doAnything();
    }
}