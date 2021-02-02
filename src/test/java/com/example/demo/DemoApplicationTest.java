package com.example.demo;

import com.example.demo.mapper.quartz.QuartzJobLogMapper;
import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.stream.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.demo.utils.SqlHelper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import org.apache.ibatis.cursor.Cursor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;
import org.springframework.test.context.junit4.SpringRunner;

/**
    *@ClassName: DemoApplicationTest
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/24 16:42
    */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class DemoApplicationTest {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @Autowired
  private QuartzJobLogMapper quartzJobLogMapper;

  @Autowired
  private SqlHelper sqlHelper;

  @Test
  public void elasticSearchTest() throws IOException {
    //参数为索引名，可以不指定，可以一个，可以多个
    DeleteByQueryRequest request = new DeleteByQueryRequest("megacorp");
    // 更新时版本冲突
    request.setConflicts("proceed");
    // 设置查询条件，第一个参数是字段名，第二个参数是字段的值
    BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
    MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("first_name", "John");
    boolBuilder.must(matchQueryBuilder);
    request.setQuery(boolBuilder);
    // 更新最大文档数
    request.setSize(10);
    // 批次大小
    request.setBatchSize(1000);
    // 并行
    request.setSlices(2);
    // 使用滚动参数来控制“搜索上下文”存活的时间
    request.setScroll(TimeValue.timeValueMinutes(10));
    // 超时
    request.setTimeout(TimeValue.timeValueMinutes(2));
    // 刷新索引
    request.setRefresh(true);
    try {
      BulkByScrollResponse response = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
      System.out.println(response.getStatus().getUpdated());
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        restHighLevelClient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void sqlTest1(){
    Map<String,Object> params = new HashMap<>();
    params.put("host","172.18.0.3");
    params.put("port",8080);
    quartzJobLogMapper.getList(params);
    String sql = null;
    try {
      sql = sqlHelper.getNamespaceSql(quartzJobLogMapper.getClass().getMethod("getList", Map.class).getName(),params);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    System.out.println(sql);
  }

  @Test
  public void sqlTest() throws NoSuchMethodException {
    Map<String,Object> params = new HashMap<>();
    params.put("host","172.18.0.3");
    params.put("port",8080);
    String sql = "select * from quartz_job_log where host =  '${host}' and port = '${port}'";
    Cursor<QuartzJobLog> quartzJobLogs =  quartzJobLogMapper.getComplexList(sql,params);
    quartzJobLogs.forEach(quartzJobLog -> {
      System.out.println(quartzJobLog.toString());
    });
    String resultSql = sqlHelper.getMapperSql(quartzJobLogMapper,"getComplexList",sql,params);
    System.out.println(resultSql);

  }

 /* @Test
  public void test3() {
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    sourceBuilder.fetchSource(new String[]{"*"}, Strings.EMPTY_ARRAY);

    MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "rabbit");
    MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("YunZhiHui", "address", "company");
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("interest", "game steak");
    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("birthday");
    rangeQueryBuilder.gte("2018-01-26");
    rangeQueryBuilder.lte("2019-01-26");

    BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
    boolBuilder.must(matchQueryBuilder);
    boolBuilder.must(termQueryBuilder);
    boolBuilder.must(rangeQueryBuilder);
    boolBuilder.should(multiMatchQueryBuilder);

    sourceBuilder.query(boolBuilder);
    SearchRequest searchRequest = new SearchRequest(index);
    searchRequest.types(type);
    searchRequest.source(sourceBuilder);
    try {
      SearchResponse response = client.search(searchRequest);
      System.out.println(response);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }*/

  @Test
  public void collectionToMap(){
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
    Map<Integer, Student> collect_2 = list.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (a, b) -> a.getAge() > b.getAge() ? a : b));
    for (Integer key : collect_2.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_2.get(key));
    }
  }

  private List<Student> getStudentList(){
    List<Student> sList = new ArrayList<>();
    sList.add(new Student(1, "lisi", 5));
    sList.add(new Student(2, "wangwu", 7));
    sList.add(new Student(3, "maliu", 9));
    return sList;
  }

  @Test
  public void FunctionTest() {
    Function function = o -> null;
    System.out.println(function.apply(1));
  }
}
