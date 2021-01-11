package com.example.demo.pojo.test;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "`test`")
public class Test1 {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 
     */
    @Column(name = "`name`")
    private String name;

    @Column(name = "`amount_count`")
    private BigDecimal amountCount;

    @Column(name = "name_1")
    private String name1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmountCount() {
        return amountCount;
    }

    public void setAmountCount(BigDecimal amountCount) {
        this.amountCount = amountCount;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}