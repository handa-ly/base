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

    public QuartzJobLog(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public QuartzJobLog() {
    }

    @Override
    public String toString() {
        return "QuartzJobLog{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                "} " + super.toString();
    }
}