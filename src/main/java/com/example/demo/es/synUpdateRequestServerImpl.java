package com.example.demo.es;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/4/15 17:18
 * @Version:1.0
 * @Description:
 */
@Service
public class synUpdateRequestServerImpl {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    public void test(){
        UpdateRequest request = new UpdateRequest ("gb", "doc", "1");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        jsonMap.put("test", "test update");
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
            if(updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                // 任何一个字段的更新，都算更新操作，即使只是日期字段的值变化
                System.out.println("文档更新成功!");
            }
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
}