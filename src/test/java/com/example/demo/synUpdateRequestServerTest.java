package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

/**
 * @Author: hanDa
 * @Date: 2021/4/15 17:55
 * @Version:1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class synUpdateRequestServerTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void test() {
        UpdateRequest request = new UpdateRequest("blog", "1");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", "title_title");
        request.doc(jsonMap);
        // true，表明如果文档不存在，则新更新的文档内容作为新的内容插入文档，这个和scriptedUpsert的区别是：更新文档的两种不同方式，有的使用doc方法更新有的使用脚本更新
        request.docAsUpsert(false);
        // 为true，表明无论文档是否存在，脚本都会执行（如果不存在时，会创建一个新的文档）
        request.scriptedUpsert(false);
        // 等待主分片可用的超时时间
        request.timeout(TimeValue.timeValueMinutes(300));
        //WAIT_UNTIL 一直保持请求连接中，直接当所做的更改对于搜索查询可见时的刷新发生后，再将结果返回
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        // 设置希望在返回结果中返回的字段值
        String[] includes = new String[]{"updated", "r*"};
        String[] excludes = Strings.EMPTY_ARRAY;
        try {
            UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                // 任何一个字段的更新，都算更新操作，即使只是日期字段的值变化
                System.out.println("文档更新成功!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() {
        extracted();
        while (true){

        }

    }

    private void extracted() {
        List<String> queryList = new ArrayList<>();
        queryList.add("1");
        queryList.add("2");
        Map<JSONObject, JSONObject> map = new HashMap<>();
        JSONObject q = new JSONObject();
        JSONObject u = new JSONObject();
        q.putIfAbsent("_id", "1");
        u.putIfAbsent("content", "content11111");
        JSONObject q1 = new JSONObject();
        JSONObject u1 = new JSONObject();
        q1.putIfAbsent("_id", "2");
        u1.putIfAbsent("content", "content222222");
        map.put(q, u);
//        map.put(q1, u1);
        updateByQuery("blog", queryList, map,map);
    }

    public void updateByQuery(String index, List<String> queryList, Map<JSONObject, JSONObject> data,Map document) {
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(index);
        updateByQueryRequest.setQuery(QueryBuilders.termsQuery("_id", queryList));
        updateByQueryRequest.setScript(new Script(packScript(data)));
        restHighLevelClient.updateByQueryAsync(updateByQueryRequest, RequestOptions.DEFAULT, new ActionListener<>() {
            @Override
            public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
                System.out.println(bulkByScrollResponse.getUpdated());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static String packScript(Map<JSONObject, JSONObject> data) {
        StringBuilder sb = new StringBuilder();
        if (MapUtils.isNotEmpty(data)) {
            data.forEach((k, v) -> {
                StringBuilder script = new StringBuilder();
                //k
                Set<String> qs = k.keySet();
                if (qs.size() > 0) {
                    script.append("if (");
                    var sj = new StringJoiner(" && ");
                    for (String q : qs) {
                        StringBuilder s = new StringBuilder();
                        if (StringUtils.equals(q,"_id")){
                            sj.add(s.append(" ctx.").append(q).append("==").append("'").append(k.get(q)).append("'"));
                        }else {
                            sj.add(s.append(" ctx._source.").append(q).append("==").append("'").append(k.get(q)).append("'"));
                        }
                    }
                    script.append(sj).append(" )");
                    //v
                    Set<String> us = v.keySet();
                    if (us.size() > 0) {
                        script.append(" {");
                        for (String u : us) {
                            String appendValue = "";
                            Object value = v.get(u);
                            if (value instanceof Number) {
                                appendValue = value.toString();
                            } else if (value instanceof String) {
                                appendValue = "'" + value.toString() + "'";
                            } else if (value instanceof List) {
                                appendValue = JSON.toJSONString(value);
                            } else {
                                appendValue = value.toString();
                            }
                            script.append("ctx._source.").append(u).append("=").append(appendValue).append(";");
                        }
                        script.append(" }");
                        sb.append(script);
                    }
                }
            });
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        extracted1();
        script();
    }

    private static void extracted1() {
        Map<JSONObject, JSONObject> map = new HashMap<>();
        JSONObject q = new JSONObject();
        JSONObject u = new JSONObject();
        q.putIfAbsent("_id", "1");
        u.putIfAbsent("content", "content1");
        JSONObject q1 = new JSONObject();
        JSONObject u1 = new JSONObject();
        q1.putIfAbsent("_id", "2");
        u1.putIfAbsent("content", "content2");
        map.put(q, u);
        map.put(q1, u1);
        System.out.println(packScript(map));
    }

    public static void script(){
        Map document = new HashMap<>();
        document.put("content", "7");
        StringBuilder script = new StringBuilder();
        Set<String> keys = document.keySet();
        for (String key : keys) {
            String appendValue = "";
            Object value = document.get(key);
            if (value instanceof Number) {
                appendValue = value.toString();
            } else if (value instanceof String) {
                appendValue = "'" + value.toString() + "'";
            } else if (value instanceof List) {
                appendValue = JSON.toJSONString(value);
            } else {
                appendValue = value.toString();
            }
            script.append("ctx._source.").append(key).append("=").append(appendValue).append(";");
        }
        System.out.println(script.toString());
    }
}