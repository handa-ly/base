package com.example.demo.concurrent;/**
 * @Author handa
 * Description:
 * Date: Created in 17:23 2020/1/16
 * Modified By:
 */

//import lombok.Data;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/16 17:23
 */
//@Data
public class Psersion {
    volatile int id;

    public Psersion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
