package com.example.demo.mybatis.dync;/**
 * @Author handa
 * Description:
 * Date: Created in 14:51 2020/7/28
 * Modified By:
 */

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/7/28 14:51
 * @Version:1.0
 * @Description:
 */
@Data
public class SqlWhere {
    private String columnName;
    private String value;
    private String condition;

    public SqlWhere(String columnName, String value, String condition) {
        this.columnName = columnName;
        this.value = value;
        this.condition = condition;
    }
}