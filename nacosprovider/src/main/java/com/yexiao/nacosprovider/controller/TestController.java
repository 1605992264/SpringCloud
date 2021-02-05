package com.yexiao.nacosprovider.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/9/15 13:50
 **/
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/restful/{name}")
    public String hello(@PathVariable("name") String name){
        return "hello " + name;
    }

    @GetMapping("/get")
    public String get(@RequestParam String name){
        throw new RuntimeException();
//        return port + "get" + name;
    }

    @PostMapping("/post")
    public String post(Test test){
        return JSONObject.toJSONString(test);
    }

    @PostMapping("/testForm")
    public String post(@RequestParam Map<String,Object> test){
        return JSONObject.toJSONString(test);
    }

    @PostMapping("/testJson")
    public String postJson(@RequestBody Map<String,Object> test){
        return JSONObject.toJSONString(test);
    }

    @PostMapping("/testFile")
    public String file(MultipartFile file,String name){
        boolean empty = file.isEmpty();
        return String.valueOf(!empty) + name;
    }

}
