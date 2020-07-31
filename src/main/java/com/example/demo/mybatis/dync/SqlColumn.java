package com.example.demo.mybatis.dync;/**
 * @Author handa
 * Description:
 * Date: Created in 14:45 2020/7/28
 * Modified By:
 */

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/7/28 14:45
 * @Version:1.0
 * @Description:
 */
@Data
public class SqlColumn {
    private String columnName;
    private String name;

    public SqlColumn(String columnName, String name) {
        this.columnName = columnName;
        this.name = name;
    }

    public SqlColumn() {
    }
}