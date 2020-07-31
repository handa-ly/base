package com.example.demo.mybatis.dync;/**
 * @Author handa
 * Description:
 * Date: Created in 14:48 2020/7/28
 * Modified By:
 */

import lombok.Data;

import java.util.List;

/**
 * @Author: hanDa
 * @Date: 2020/7/28 14:48
 * @Version:1.0
 * @Description:
 */
@Data
public class SqlTable {
    private String table;
    private String title;
    private List<SqlColumn> columnList;
    private List<SqlWhere> whereList;

    public SqlTable(String table, String title) {
        this.table = table;
        this.title = title;
    }

    public SqlTable() {
    }
}