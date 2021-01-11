package com.example.demo.controller;/**
 * @Author handa
 * Description:
 * Date: Created in 10:06 2020/8/25
 * Modified By:
 */

import com.example.demo.thread.pool.test.ThreadTest1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: hanDa
 * @Date: 2020/8/25 10:06
 * @Version:1.0
 * @Description:
 */
@RestController
public class ThreadTest {
    @Autowired
    private ThreadTest1 threadTest;

    @GetMapping("/threadTest")
    private void test(){
        ArrayBlockingQueue<List<String>> arrayBlockingQueue = new ArrayBlockingQueue<List<String>>(100);

//        threadTest.setConsumeBlockingQueue(arrayBlockingQueue);
        // 消费数据
        for(int i=0;i<1000;i++){
            new Thread(threadTest).start();
        }
    }

    @PostMapping("/mapTest")
    private void mapTest(@RequestBody  List<Map<String,Object>> mapList){
        mapList.forEach(longIntegerMap -> System.out.println(longIntegerMap.toString()));
    }
}