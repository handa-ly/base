package com.example.demo.io.decorator;/**
 * @Author handa
 * Description:
 * Date: Created in 20:33 2020/1/7
 * Modified By:
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/7 20:33
 */
public class InputTests {
    public static void main(String[] args) throws IOException {
       /* int c;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("D:\\log\\14\\1063_testTask.log")));
        while ((c= in.read()) != -1){
            System.out.printf(String.valueOf((char)c));
        }*/
        char x = 'a';
        System.out.println((int) x);

    }
}
