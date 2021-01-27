package com.example.demo.designMode.interpreter.busCardTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 14:37
 * @Version:1.0
 * @Description:
 */
public class TerminalExpression implements Expression{
    private Set<String> set = new HashSet<String>();
    public TerminalExpression(String[] data) {
        Collections.addAll(set, data);
    }
    @Override
    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        }
        return false;
    }
}