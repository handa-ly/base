package com.example.demo.designMode.interpreter;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 11:54
 * @Version:1.0
 * @Description:
 */
public class TerminalExpression implements Expression{
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)){
            return true;
        }
        return false;
    }
}