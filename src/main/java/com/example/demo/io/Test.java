package com.example.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Author: hanDa
 * @Date: 2021/1/5 11:46
 * @Version:1.0
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        /*char c;
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');*/
        System.out.println("nextLine方式接收：");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String ss = scanner.next();
            System.out.println(ss);
        }
//        scanner.close();
    }
}