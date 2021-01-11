package com.example.demo.designMode.singleton;

/**
 * @Author: hanDa
 * @Date: 2020/11/9 11:38
 * @Version:1.0
 * @Description:
 */
public class JK {
    private static  JK jk = null;

    private JK() {
    }

    //对象初始化时需要时间的
    public  static  JK  getInstance(){
        if (jk == null){
            synchronized(jk) {
                if (jk == null){
                    jk = new JK();
                }
            }
        }
        return jk;
    }


}