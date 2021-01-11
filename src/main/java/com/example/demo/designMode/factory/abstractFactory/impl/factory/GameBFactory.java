package com.example.demo.designMode.factory.abstractFactory.impl.factory;

import com.example.demo.designMode.factory.abstractFactory.Game;
import com.example.demo.designMode.factory.abstractFactory.GameFactory;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game11;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game12;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game21;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game22;

/**
 * @Author: hanDa
 * @Date: 2020/11/18 11:51
 * @Version:1.0
 * @Description:
 */
public class GameBFactory implements GameFactory {
    @Override
    public Game12 createGame1() {
        return null;
    }

    @Override
    public Game22 createGame2() {
        return null;
    }
}