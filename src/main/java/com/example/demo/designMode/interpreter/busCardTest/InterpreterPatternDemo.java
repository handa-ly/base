package com.example.demo.designMode.interpreter.busCardTest;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 14:39
 * @Version:1.0
 * @Description:
 *                *文法规则
 *                <expression> ::= <city>的<person>
 *                <city> ::= 韶关|广州
 *                <person> ::= 老人|妇女|儿童
 * */
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        Context bus = new Context();
        bus.freeRide("韶关的老人");
        bus.freeRide("韶关的年轻人");
        bus.freeRide("广州的妇女");
        bus.freeRide("广州的儿童");
        bus.freeRide("山东的儿童");
    }
}