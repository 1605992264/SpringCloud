package com.yexiao.client.openfeign.controller;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.client.base.R;
import com.yexiao.client.openfeign.mapper.TestMapper;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2021/2/1 14:59
 **/
@RestController
@RequestMapping("feign")
public class FeignController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("hello")
    public String hello(String name){
        return testMapper.hello(name);
    }

    @GetMapping("get")
    public R get(String name){
        return testMapper.get(name);
    }

    @GetMapping("testForm")
    public R testForm(String id){
        Map<String,Object> map = new HashMap(2);
        map.put("id",id);
        map.put("name","form");
        return testMapper.testForm(map);
    }

    @GetMapping("testJson")
    public R testJson(String id){
        Map<String,Object> map = new HashMap(2);
        map.put("id",id);
        map.put("name","json");
        return testMapper.testJson(map);
    }

    @GetMapping("testFile")
    public String testFile(MultipartFile file) throws IOException {
        MultiValueMap<String,Object> map = new LinkedMultiValueMap();
        map.add("file",new FileSystemResource("D://a.txt"));
        return testMapper.testFile(map);
    }

}
