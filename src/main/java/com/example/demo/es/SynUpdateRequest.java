package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.rest.RestStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/4/15 17:08
 * @Version:1.0
 * @Description:
 */
public class SynUpdateRequest {
    public static void main(String[] args) {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")
                )
        )) {

            UpdateRequest request = new UpdateRequest ("posts", "doc", "31");
//            UpdateRequest request = new UpdateRequest("posts", "type", "does_not_exist").doc("field", "value");
            // jsonMap和jsonString只是两种不同的传参方式，可以相互转换使用，效果相同
            // jsonMap内容会自动转换成json格式
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("updated", new Date());
            jsonMap.put("reason", "daily update");
            jsonMap.put("test", "test update");
            request.doc(jsonMap);
            // true，表明如果文档不存在，则新更新的文档内容作为新的内容插入文档，这个和scriptedUpsert的区别是：更新文档的两种不同方式，有的使用doc方法更新有的使用脚本更新
            request.docAsUpsert(true);
            // 为true，表明无论文档是否存在，脚本都会执行（如果不存在时，会创建一个新的文档）
            request.scriptedUpsert(true);
            // 如果文档不存在，使用upsert方法，会根据更新内容创建新的文档
            // 需要更新的内容，以json字符串方式提供
            String jsonString = "{\"created\":\"2017-01-01\"}";
            request.upsert(jsonString, XContentType.JSON);

            // 等待主分片可用的超时时间
            request.timeout(TimeValue.timeValueMinutes(300));
            //WAIT_UNTIL 一直保持请求连接中，直接当所做的更改对于搜索查询可见时的刷新发生后，再将结果返回
            request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
            // 如果更新的过程中，文档被其它线程进行更新的话，会产生冲突，这个为设置更新失败后重试的次数
            request.retryOnConflict(3);
            // 是否将文档内容作为结果返回，默认是禁止的
            request.fetchSource(true);
            // 设置希望在返回结果中返回的字段值
            String[] includes = new String[]{"updated", "r*"};
            String[] excludes = Strings.EMPTY_ARRAY;
//            request.fetchSource(new FetchSourceContext(false, includes, excludes));
            // NO OPeration,空操作检查,默认情况为true，只有原来的source和新的source存在不同的字段情况下才会重建索引，如果一模一样是不会触发重建索引的，如果将detect_noop=false不管内容有没有变化都会重建索引，这一点可以通过version的值的变化来发现
            request.detectNoop(true);

            // 设置在更新操作执行之前，要求活动状态的分片副本数；单机不要设置，否则会报错：超时
//            request.waitForActiveShards(2);
//            request.waitForActiveShards(ActiveShardCount.ALL);

            UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("文档创建成功!");
            }else if(updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                // 任何一个字段的更新，都算更新操作，即使只是日期字段的值变化
                System.out.println("文档更新成功!");
            }else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
                System.out.println("文档删除成功!");
            } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
                // 如果request.detectNoop(true);中设置为false，则这个永远不会进入
                System.out.println("文档无变化!");
            }
            String index = updateResponse.getIndex();
            String type = updateResponse.getType();
            String id = updateResponse.getId();
            long version = updateResponse.getVersion();
            System.out.println("index:" + index + "; type:" + type + "; id:" + id + ",version:" + version);
            ReplicationResponse.ShardInfo shardInfo = updateResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                System.out.println("未完全执行所有分片,总分片数为：" + shardInfo.getTotal() + ",执行的分片数为："+ shardInfo.getSuccessful());
            }
            // fetchSource 如果设置需要返回结果中包含内容了,如果没有设置返回内容，则result 等于null
            GetResult result = updateResponse.getGetResult();
            if(result == null) {
                System.out.println("无内容结果返回");
            }else if (result.isExists()) {
                // 此例中如果文档不存在，且这样设置：request.scriptedUpsert(true);、request.docAsUpsert(false);，则会创建一个空内容的文档，因为脚本中没有内容，而禁止doc创建新文档
                String sourceAsString = result.sourceAsString();
                System.out.println(sourceAsString);
                Map<String, Object> sourceAsMap = result.sourceAsMap();
            }

        }catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                // 如果不使用request.upsert方法，且request.scriptedUpsert(false);和request.docAsUpsert(false);都设置为false，则文档不存在时提示没有找到文档
                System.out.println("文档不存在");
            }else if(e.status() == RestStatus.CONFLICT) {
                System.out.println("需要删除的文档版本与现在文档冲突!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}