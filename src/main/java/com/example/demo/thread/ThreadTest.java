package com.example.demo.thread;/**
 * @Author handa
 * Description:
 * Date: Created in 17:49 2020/1/8
 * Modified By:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/8 17:49
 */
public class ThreadTest {
    public static void main(String[] args) {
        List<Integer> ss = new ArrayList<>();
        ss.stream().forEach(sss->{
            System.out.println(sss);
        });
    }
}
