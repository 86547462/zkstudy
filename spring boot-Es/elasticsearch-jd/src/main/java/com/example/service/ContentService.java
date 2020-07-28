package com.example.service;
import com.alibaba.fastjson.JSON;
import com.example.pojo.Content;
import com.example.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 业务编写
 */
@Service
public class ContentService {
    @Qualifier("restHighLevelClient")
    @Autowired
     RestHighLevelClient clintConfig;

    //判断索引是否存在
     public boolean isExists() throws IOException {
         GetIndexRequest request=new GetIndexRequest("jingdongs");
         // boolean exists=clintConfig.indices().exists(request,RequestOptions.DEFAULT);
        return  clintConfig.indices().exists(request,RequestOptions.DEFAULT);
     }

    //1.将解析数据放在es搜索引擎中
    public Boolean parseContent(String keywords) throws IOException {
         //不存在创建索引
         if(!isExists())
         {
             CreateIndexRequest createIndex=new CreateIndexRequest("jingdongs");
             CreateIndexResponse createIndexResponse=
                     clintConfig.indices().create(createIndex,RequestOptions.DEFAULT);
             //System.out.print("索引创建返回值>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + createIndexResponse);
         }
        List<Content> list=new HtmlParseUtil().parseJD(keywords);
        //吧查询的数据放入es中
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("2m");
        for (Content content : list) {
            bulkRequest.add(
                    new IndexRequest("jingdongs")
                            .source(JSON.toJSONString(content), XContentType.JSON));

        }
        BulkResponse bulkResponse=clintConfig.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }


    //获取数据实现搜索功能
    public List<Map<String,Object>> search(String keyword,int page,int limit) throws IOException {
        //System.out.print(">>>>>>>>>>>>>>>>>>>>>>>"+isExists());
        if(page<=1)
        {
            page=1;
        }
        //条件查询
        SearchRequest searchRequest=new SearchRequest("jingdongs");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //分页
        sourceBuilder.from(page);
        sourceBuilder.size(limit);
        //设置高亮
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);//过个高亮显示
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        //精准匹配QueryBuilders.termQuery("hotelName","hotel")
        //多termsQuery查询
        //模糊查询fuzzyQuery
        //MatchQueryBuilder termQueryBuilder= QueryBuilders.matchQuery("title",keyword);
        TermQueryBuilder termQueryBuilder= QueryBuilders.termQuery("title",keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=clintConfig.search(searchRequest,RequestOptions.DEFAULT);

        //解析结果

        ArrayList<Map<String,Object>> list=new ArrayList<>();

        for (SearchHit hit:searchResponse.getHits().getHits()
             ) {
            //解析高亮的字段
            Map<String,HighlightField>  highlightFields=hit.getHighlightFields();//原来的结果
            HighlightField title=highlightFields.get("title");
            Map<String,Object> sourceAsMap=hit.getSourceAsMap();//原来的结果

            //解析高亮的字段，将原来的字段替换成我们写的高亮的字段即可
            if(title!=null)
            {
                Text[] fragments = title.fragments();
                String n_title="";
                for(Text text:fragments)
                {
                    n_title+=text;
                }
                sourceAsMap.put("title",n_title);//替换
            }


            list.add(hit.getSourceAsMap());

        }

        //System.out.print("list>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list);
        return list;

    }
    //查询全部数据
    public List<Map<String,Object>> searchAll(int page,int limit) throws IOException {
        //System.out.print(">>>>>>>>>>>>>>>>>>>>>>>"+isExists());
        if(page<=1)
        {
            page=1;
        }
        //条件查询
        SearchRequest searchRequest=new SearchRequest("jingdongs");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //分页
        sourceBuilder.from(page);
        sourceBuilder.size(limit);
        MatchAllQueryBuilder termQueryBuilder= QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=clintConfig.search(searchRequest,RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list=new ArrayList<>();
        for (SearchHit hit:searchResponse.getHits().getHits()
        ) {
            list.add(hit.getSourceAsMap());
        }
        //System.out.print("list>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list);
        return list;

    }


}
