package com.example.demo.thread;/**
 * @Author handa
 * Description:
 * Date: Created in 17:46 2020/1/8
 * Modified By:
 */

/**
 * @description:
 * @author: handa
 * @time: 2020/1/8 17:46
 */
public class User {
    private String name;

    public synchronized void setName(String name, Long min) {
        this.name = name;
        /*try {
            Thread.sleep(min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+": "+System.currentTimeMillis());*/
    }

    public synchronized void setName1(String name1) {
        this.name = name1;
    }

    public String getName() {
        return name;
    }


}
