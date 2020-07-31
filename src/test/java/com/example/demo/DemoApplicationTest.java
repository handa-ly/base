package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.aop.aspectJ.TargetObject;
import com.example.demo.base.BaseMapper;
import com.example.demo.mybatis.dync.SqlColumn;
import com.example.demo.mybatis.dync.SqlHelper;
import com.example.demo.mybatis.dync.SqlTable;
import com.example.demo.mybatis.dync.SqlWhere;
import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.spring.CustomBeanFactoryPostProcessor;
import com.example.demo.stream.Student;
import com.example.demo.util.CamelAndUnderLineConverterUtil;
import com.google.common.base.Joiner;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: DemoApplicationTest
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/24 16:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
//@EnableAspectJAutoProxy
public class DemoApplicationTest {


  public static void main(String[] args) {
    String ss = "{\"ss\":\"ss\",\"ss\":\"ss\"}";
    System.out.println(JSON.parse(ss).toString());
  }
  @Autowired
  TargetObject targetObject;
  @Autowired
  private CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor;

  @Autowired
  private BaseMapper baseMapper;

  @Autowired
  private SqlSessionFactory sqlSessionFactory;
  @Test
  public void collectionToMap() {
    List<Student> list = getStudentList();
    list.add(new Student(1, "xixi", 12));
//     java.lang.IllegalStateException: Duplicate key Student(id=1, name=lisi, age=5) 有异常
//        Map<Integer, Student> collect = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));

    //源码给出的解决办法 key 为ID value为name 对于key相同的value 使用逗号拼接,适用于一个联系人同名，电话不同
 /*   Map<Integer, String> collect_ = list.stream().collect(
        Collectors.toMap(Student::getId, Student::getName, (s, a) -> s + ", " + a));
    for (Integer key : collect_.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_.get(key));
    }
    System.out.println("'==================='");

    Map<Integer, Student> collect_1 = list.stream().collect(Collectors.toMap(Student::getId, Function
        .identity(), (a, b) -> a.equals(b) && a.hashCode() == b.hashCode() ? a : b));
    for (Integer key : collect_1.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_1.get(key));
    }
    System.out.println("'==================='");*/

    // 判断一个对象是否相同equals为true 并且hashcode相同
    Map<Integer, Student> collect_2 = list.stream().collect(Collectors
        .toMap(Student::getId, Function.identity(), (a, b) -> a.getAge() > b.getAge() ? a : b));
    for (Integer key : collect_2.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_2.get(key));
    }
  }

  private List<Student> getStudentList() {
    List<Student> sList = new ArrayList<>();
    sList.add(new Student(1, "lisi", 5));
    sList.add(new Student(2, "wangwu", 7));
    sList.add(new Student(3, "maliu", 9));
    return sList;
  }

  @Test
  public void aspectTest() throws ClassNotFoundException {
    SqlTable sqlTable = new SqlTable("com.example.demo.pojo.quartz.QuartzJobLog","表名");
    List<SqlColumn> sqlColumnList = new ArrayList<>();
    SqlColumn sqlColumn = new SqlColumn("host","HOST");
    sqlColumnList.add(sqlColumn);
    sqlTable.setColumnList(sqlColumnList);
    List<SqlWhere> sqlWhereList = new ArrayList<>();
    SqlWhere sqlWhere = new SqlWhere("host","172.18.0.3","=");
    sqlWhereList.add(sqlWhere);
    sqlTable.setWhereList(sqlWhereList);


//    Example example = new Example(CustomBeanFactoryPostProcessor.getBean(sqlTable.getTable()).getClass());
    Class clazz = Class.forName(sqlTable.getTable());
    Example example = new Example(clazz);

    List<SqlColumn> sqlColumns = sqlTable.getColumnList();
    List<String> columnNameList = sqlColumns.stream().map(SqlColumn::getColumnName).collect(Collectors.toList());
    example.selectProperties(Joiner.on(",").join(columnNameList));
    Example.Criteria criteria = example.createCriteria();
    List<SqlWhere> sqlWhereList1 = sqlTable.getWhereList();
    if (!CollectionUtils.isEmpty(sqlWhereList1)){
      sqlWhereList1.forEach(sqlWhere1 -> {
        String columnName = sqlWhere1.getColumnName();
        if(sqlWhere1.getCondition() == "="){
          criteria.andEqualTo(columnName,sqlWhere.getValue());
        }
      });
    }
//    BaseMapper baseMapper = (BaseMapper) CustomBeanFactoryPostProcessor.getBean(CamelAndUnderLineConverterUtil.lowerFirst(clazz.getSimpleName()).concat("Mapper"));
    List<Object> quartzJobLogList = baseMapper.selectByExample(example);
    baseMapper.getClass().getInterfaces()[0].getSimpleName();
    MappedStatement mappedStatement = sqlSessionFactory.getConfiguration().
            getMappedStatement(baseMapper.getClass().getInterfaces()[0].getName().concat(".").concat("selectByExample"));
    /*String sql = mappedStatement.
            getBoundSql(example).getSql();*/
    SqlHelper sqlHelper = new SqlHelper();
    sqlHelper.setSqlSessionFactory(sqlSessionFactory);
    String sql = sqlHelper.getNamespaceSql(baseMapper.getClass().getInterfaces()[0].getName().concat(".selectByExample"), example);
//    Object parameter = mappedStatement.getParameterHandler().getParameterObject();
    List<Map<String,Object>> returnList= new ArrayList<>();
    quartzJobLogList.forEach(targetObject ->{
      Map<String, Object> returnMap = new HashMap<>();
      JSONObject jsonObject = (JSONObject) JSONObject.toJSON(targetObject);
      sqlColumns.forEach(sqlColumn1 -> {
        String name = sqlColumn1.getName();
        String columnName = sqlColumn1.getColumnName();
        if(StringUtils.isEmpty(name)){
          returnMap.put(columnName,jsonObject.get(columnName));
        }else {
          returnMap.put(name,jsonObject.get(columnName));
        }
      });
      returnList.add(returnMap);
    });
    System.out.printf(returnList.toString());
  }

  @Test
  public void classColumnTest() throws ClassNotFoundException {
    Class clazz = Class.forName("com.example.demo.pojo.quartz.QuartzJobLog");
    Set<EntityColumn> columnSet = EntityHelper.getColumns(clazz);
    Map<String,Object> map = baseMapper.getTableDetail();
    System.out.println(toString(map));
  }


  @Test
  public void exeSqlTest() {
    Map<String,Object> map = baseMapper.selectBySQL("select * from sys_user");
    System.out.println(toString(map));
  }
  public String toString(Object object) {
    return JSON.toJSONString(object,
            SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.UseISO8601DateFormat);
  }
}