package com.example.controller;

import com.example.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 前端请求编写
 */
@RestController
public class ContentController {

    @Autowired
    ContentService service;

    //添加到es
    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable("keywords") String kaywords) throws IOException {
        return service.parseContent(kaywords);
    }
    //查询
    @GetMapping("/search/{keyword}/{page}/{limit}")
    public List<Map<String,Object>> search(@PathVariable("keyword") String keyword,
                                           @PathVariable("page") int page,
                                           @PathVariable("limit") int limit) throws IOException {
        if(page==0) { page=1; }
        if(limit==0) { limit=10; }
        return service.search(keyword,page,limit);
    }
    //查询全部数据，无参数
    @GetMapping("/searchAll/{page}/{limit}")
    public List<Map<String,Object>> searchAll(
            @PathVariable("page")int page,
            @PathVariable("limit")int limit
    ) throws IOException {
        if(page==0) { page=1; }
        if(limit==0) { limit=10; }
        return service.searchAll(page,limit);
    }



}
