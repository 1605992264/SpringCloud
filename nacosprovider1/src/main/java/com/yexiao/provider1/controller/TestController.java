package com.yexiao.provider1.controller;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.provider1.base.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public R get(@RequestParam String name){
        return R.success(name);
    }

    @PostMapping("/post")
    public String post(Test test){
        return JSONObject.toJSONString(test);
    }

    @PostMapping("/testForm")
    public R post(@RequestParam Map<String,Object> test){
        return R.success(test);
    }

    @PostMapping("/testJson")
    public R postJson(@RequestBody Map<String,Object> test){
        return R.success(test);
    }

    @PostMapping("/testFile")
    public String file(MultipartFile file,String name){
        boolean empty = file.isEmpty();
        return String.valueOf(!empty) + name;
    }

}
