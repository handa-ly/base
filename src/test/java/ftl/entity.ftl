package com.example.demo.pojo.${packagePath};

import com.example.demo.pojo.BaseEntity;

import javax.persistence.Table;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`${tableName}`")
public class ${className} extends BaseEntity {

<#list columnList as col>
    <#if col.changeColumnName !="id" && col.changeColumnName !="createTime" && col.changeColumnName !="amendTime">
    /**
     * ${col.columnComment}
     */
    @Column(name = "`${col.columnName}`")
    private ${col.type} ${col.changeColumnName};

    </#if>
</#list>
<#list columnList as col>
    <#if col.changeColumnName !="id" && col.changeColumnName !="createTime" && col.changeColumnName !="amendTime">
    public ${col.type} get${col.CapitalColumnName}() {
        return ${col.changeColumnName};
    }

    public void set${col.CapitalColumnName}(${col.type} ${col.changeColumnName}) {
        this.${col.changeColumnName} = ${col.changeColumnName};
    }

    </#if>
</#list>


}