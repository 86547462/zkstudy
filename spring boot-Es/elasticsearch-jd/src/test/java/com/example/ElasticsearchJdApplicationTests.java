package com.example;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElasticsearchJdApplicationTests {
    @Qualifier("restHighLevelClient")
    @Autowired
    public RestHighLevelClient clintConfig;

    @Test
    void contextLoads() throws IOException {
//        //条件查询
//        SearchRequest searchRequest=new SearchRequest("jd_goods");
//        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
//
//        //精准匹配QueryBuilders.termQuery("hotelName","hotel")
//        //多termsQuery查询
//        //模糊查询fuzzyQuery
//        MatchQueryBuilder termQueryBuilder= QueryBuilders.matchQuery("title","java");
//        sourceBuilder.query(termQueryBuilder);
//        //执行搜索
//        searchRequest.source(sourceBuilder);
//        SearchResponse searchResponse=clintConfig.search(searchRequest, RequestOptions.DEFAULT);
//        //解析结果
//        ArrayList<Map<String,Object>> list=new ArrayList<>();
//
//        for (SearchHit hit:searchResponse.getHits().getHits()
//        ) {
//            System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+hit.getSourceAsMap());
//            list.add(hit.getSourceAsMap());
//        }

        SearchRequest request=new SearchRequest("jd_goods");
        //构造查询条件
        SearchSourceBuilder searchBuilder=new SearchSourceBuilder();
        //查询条件，使用QUeryBuliders工具实现
       // TermQueryBuilder termBuilder= QueryBuilders.termQuery("title","java");
       // TermQueryBuilder termBuilder= QueryBuilders.termQuery("title","Vue.js实战 Vue.js实战");
        FuzzyQueryBuilder termBuilder= QueryBuilders.fuzzyQuery("title","Vue");
        searchBuilder.query(termBuilder);
        searchBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(searchBuilder);
        SearchResponse searchResponse=clintConfig.search(request,RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("----------------->");
        for (SearchHit documentFileids:searchResponse.getHits().getHits()
        ) {
            System.out.println(">>>>>>"+documentFileids.getSourceAsMap());
        }
    }

}
