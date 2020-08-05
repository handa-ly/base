package com.example.demo.mybatis.dync.annotaion;

import java.lang.annotation.*;

/**
 * @Author handa
 * Description:
 * Date: Created in 20:52 2020/8/3
 * Modified By:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynaEntity {
    /**
     * @Description: 类全限定名
     * @return
     */
    String className() default "";
}
