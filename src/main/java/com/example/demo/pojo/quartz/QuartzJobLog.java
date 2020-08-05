package com.example.demo.pojo.quartz;

import com.example.demo.mybatis.dync.annotaion.DynaEntity;
import com.example.demo.pojo.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@DynaEntity
@Data
@Table(name = "`quartz_job_log`")
public class QuartzJobLog extends BaseEntity {

    /**
     * 
     */
    @Column(name = "`host`")
    private String host;

    /**
     * 
     */
    @Column(name = "`port`")
    private String port;

    @Transient
    private String test;

}