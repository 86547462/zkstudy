package com.example.util;

import com.example.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;


import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
//        new HtmlParseUtil().parseJD("vue").forEach(System.out::println);
//    }

    public List<Content> parseJD (String keyword) throws IOException {
        //获取请求https://search.jd.com/Search?keyword=java
        String url="https://search.jd.com/Search?keyword="+keyword;
        //解析网页
        Document document=Jsoup.parse(new URL(url),3000);
        //所有js的方法都能用
        Element element=document.getElementById("J_goodsList");
         //获取所有li标签
        Elements elements=element.getElementsByTag("li");
//        System.out.println(element.html());
        List<Content> list=new ArrayList<>();

        //获取元素的内容
        for (Element el:elements
             ) {
            //关于图片特别多的网站，所有图片都是延迟加载的
            String img=el.getElementsByTag("img").eq(0).attr("src");
            String price=el.getElementsByClass("p-price").eq(0).text();
            String title=el.getElementsByClass("p-name").eq(0).text();

            Content content=new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
//            System.out.println("---------------------------->");
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);
            list.add(content);
        }
            return list;
    }
}
