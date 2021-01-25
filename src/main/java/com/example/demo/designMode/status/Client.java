package com.example.demo.designMode.status;

/**
 * @Author: hanDa
 * @Date: 2021/1/22 17:18
 * @Version:1.0
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}