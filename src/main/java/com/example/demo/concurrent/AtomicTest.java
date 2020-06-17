package com.example.demo.concurrent;/**
 * @Author handa
 * Description:
 * Date: Created in 15:59 2020/1/16
 * Modified By:
 */

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/16 15:59
 */
public class AtomicTest {
    private static Class<Psersion> cls;

    public static void main(String[] args) {
        /*=========AtomicInteger测试======================================*/
        AtomicInteger i = new AtomicInteger(0);
        int addUpdate = i.getAndUpdate(operand -> operand + 3);
//        System.out.println(addUpdate);
//        System.out.println(i.get());
        int Accumulate = i.getAndAccumulate(5, (left, right) -> left + right);
//        System.out.println(Accumulate);
//        System.out.println(i.get());
        /*=========AtomicIntegerFieldUpdater测试======================================*/
        AtomicIntegerFieldUpdater<Psersion> mAtoLong = AtomicIntegerFieldUpdater.newUpdater(Psersion.class, "id");
        Psersion psersion = new Psersion(32);
        mAtoLong.accumulateAndGet(psersion, 32, (a, b) -> a + b);
        System.out.println(mAtoLong.get(psersion));
    }
}
