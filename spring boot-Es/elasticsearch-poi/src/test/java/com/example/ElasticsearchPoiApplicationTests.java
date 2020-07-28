package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.example.pojo.User;
import com.example.util.ESconst;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElasticsearchPoiApplicationTests {
    /**
     * 测试，注入elasticsearch的api
     */
    @Autowired
    @Qualifier("restHighLevelClient")
    RestHighLevelClient clintConfig;
    @Test
    void contextLoads() throws IOException {
        //1.创建索引请求
        CreateIndexRequest request=new CreateIndexRequest("lesson_one");
        //2.客户端执行请求，IndicesClient，请求后获取响应
        CreateIndexResponse createIndexResponse=
                clintConfig.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("+>>>>>"+createIndexResponse);
    }

    //获取索引(判断是否存在)
    @Test
    void ExistIndex() throws IOException {
        GetIndexRequest request=new GetIndexRequest("lesson_one");
        boolean exists=clintConfig.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //删除索引
    @Test
    void delIndex() throws IOException {
        DeleteIndexRequest request=new DeleteIndexRequest("lesson_one");
        AcknowledgedResponse del=clintConfig.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(del.isAcknowledged());
    }

    //条件测试文档
    @Test
    void testAddDocument() throws IOException {
        //创建对象
        User user=new User("周坤",3);
        //创建请求
        IndexRequest request=new IndexRequest("lesson_one");

        //创建规则 put/lesson_one/doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        //将数据放进请求
        request.source(JSON.toJSONString(user), XContentType.JSON);

        //客户端发送请求
        IndexResponse indexResponse=clintConfig.index(request,RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());//对应命令返回

    }

    @Test
    void testIsExists() throws IOException {
        //获取文档是否存在
        GetRequest request=new GetRequest("lesson_one","1");
        boolean exists=clintConfig.exists(request,RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //获取文档信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest request=new GetRequest("lesson_one","1");
        GetResponse getResponse=clintConfig.get(request,RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());//打印文档的内容
        System.out.println(getResponse);//返回全部的内容和命令式一样的
    }

    //更新文档
    @Test
    void updateDocument() throws IOException {
        UpdateRequest request=new UpdateRequest("lesson_one","1");
        request.timeout("1s");
        User user=new User("张三",13);
        request.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse response=clintConfig.update(request,RequestOptions.DEFAULT);
        System.out.println(response.status());

    }
    //删除文档
    @Test
    void deleteDocument() throws IOException {
        DeleteRequest request=new DeleteRequest("lesson_one","1");
        request.timeout("1s");
        DeleteResponse deleteResponse=clintConfig.delete(request,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());


    }
    //批量插入数据
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> list=new ArrayList<>();
        list.add(new User("张三",3));
        list.add(new User("李四",19));
        list.add(new User("王五",14));
        list.add(new User("赵柳",45));
        list.add(new User("刘工",8));

        for(int i=0;i<list.size();i++)
        {
            bulkRequest.add(
                    new IndexRequest("lesson_one")
                    .id(""+(i+1))
                    .source(JSON.toJSONString(list.get(i)),XContentType.JSON));

        }
        BulkResponse bulkItemResponses=clintConfig.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(bulkItemResponses.hasFailures());//是否失败，返回false代表成功

    }
    /**
     * 查询文档(搜索)
     * SearchRequset 搜索请求
     * SerachSourBulider条件构造
     * HighLightBuilder构建高亮
     * TermQuerBuilder精确查询
     * MathAllQueryBuilder
     * xxx QueryBuilder对应我们刚才刚到的所有命令
     */
    @Test
    public void testSearch() throws IOException {
        SearchRequest request=new SearchRequest(ESconst.ES_INDEX);
        //构造查询条件
        SearchSourceBuilder searchBuilder=new SearchSourceBuilder();
        //查询条件，使用QUeryBuliders工具实现
        TermQueryBuilder termBuilder= QueryBuilders.termQuery("name","三");
        searchBuilder.query(termBuilder);
        searchBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(searchBuilder);
        SearchResponse searchResponse=clintConfig.search(request,RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("----------------->");
        for (SearchHit documentFileids:searchResponse.getHits().getHits()
             ) {
            System.out.println(documentFileids.getSourceAsMap());
        }



    }

}
