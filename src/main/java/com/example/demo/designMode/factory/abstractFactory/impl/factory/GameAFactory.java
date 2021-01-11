package com.example.demo.designMode.factory.abstractFactory.impl.factory;

import com.example.demo.designMode.factory.abstractFactory.GameFactory;
import com.example.demo.designMode.factory.abstractFactory.impl.product.AbstractGame1;
import com.example.demo.designMode.factory.abstractFactory.impl.product.AbstractGame2;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game11;
import com.example.demo.designMode.factory.abstractFactory.impl.product.Game21;

/**
 * @Author: hanDa
 * @Date: 2020/11/18 11:51
 * @Version:1.0
 * @Description:
 */
public class GameAFactory implements GameFactory {
    @Override
    public AbstractGame1 createGame1() {
        return new Game11();
    }

    @Override
    public AbstractGame2 createGame2() {
        return new Game21();
    }
}