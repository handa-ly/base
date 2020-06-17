package com.example.demo.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: lockObjectTest
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/20 9:35
 */

@Slf4j
public class LockObjectTest {
    public static void main(String[] args) {
        String fileName = "D:\\文档\\CRD\\crd测试\\兼容版本cwr\\CRD2019051615431400187788498161.020";
        LockObject lockObject = new LockObject();
        AtomicInteger j = new AtomicInteger(1);
    /*for (int i=0;i<5000;i++){
        Thread thread = new Thread(() -> {
            lockObject.test(fileName);
        });
        thread.setName("currentThread:"+i);
        thread.start();
    }*/
    // 测试锁锁定的是对象的某段代码还是整个对象
        for(int i = 0;i<10;i++){
            new Thread(() -> {
                lockObject.test("ss");
            }).start();
        }
        while (true){

        }
    }
}
