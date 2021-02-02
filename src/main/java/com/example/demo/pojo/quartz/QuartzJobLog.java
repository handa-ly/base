package com.example.demo.pojo.quartz;

import com.example.demo.pojo.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

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

    @Override
    public String toString() {
        return "QuartzJobLog{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                "} " + super.toString();
    }
}