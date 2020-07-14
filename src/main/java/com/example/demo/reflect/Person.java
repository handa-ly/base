package com.example.demo.reflect;

import java.io.Serializable;

/**
 * @Author handa
 * @Version  1.0
 * @Description
 * @Date 2020/7/14 14:07
 */
public class Person extends Parent implements Serializable,ParentInterface {

    private static final long serialVersionUID = 5749850809464630314L;
    private String name1;

    @Override
    public String commonBehaviour() {
        return null;
    }

    private String name11;
    private String name12;
    private String name13;
    private String name14;

    public Person(String name1, String name11, String name12, String name13, String name14) {
        this.name1 = name1;
        this.name11 = name11;
        this.name12 = name12;
        this.name13 = name13;
        this.name14 = name14;
    }

    public Person() {
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName11() {
        return name11;
    }

    public void setName11(String name11) {
        this.name11 = name11;
    }

    public String getName12() {
        return name12;
    }

    public void setName12(String name12) {
        this.name12 = name12;
    }

    public String getName13() {
        return name13;
    }

    public void setName13(String name13) {
        this.name13 = name13;
    }

    public String getName14() {
        return name14;
    }

    public void setName14(String name14) {
        this.name14 = name14;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name1='" + name1 + '\'' +
            ", name11='" + name11 + '\'' +
            ", name12='" + name12 + '\'' +
            ", name13='" + name13 + '\'' +
            ", name14='" + name14 + '\'' +
            '}';
    }

    @Override
    public String parentInterfaceBehaviour() {
        return null;
    }
}
