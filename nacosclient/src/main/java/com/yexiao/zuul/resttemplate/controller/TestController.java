package com.yexiao.zuul.resttemplate.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * @author xuhf
 * @date 2020/9/15 10:17
 **/
@RestController
@RequestMapping("restTemplate")
public class TestController {

    @Value("${provider.testUrl}")
    private String testUrl;

    @Autowired
    private  RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @GetMapping("get")
    public String testGet(String name){
        return restTemplate.getForObject(testUrl + "/get?name={0}",String.class,name);
    }

    public String error(String name){
        return "水水水水";
    }

    @GetMapping("testJson")
    public String postJson(String id){
        Map<String, Object> map = new HashMap<>(2);
        map.put("id",id);
        map.put("name","李四");
        return restTemplate.postForObject(testUrl + "/testJson",map,String.class);
    }

    @GetMapping("testForm")
    public String testMap(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap() ;
        map.add("id","1234");
        map.add("name","王五");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return restTemplate.postForObject(testUrl + "/testForm",new HttpEntity<>(map,httpHeaders),String.class);
    }

    @GetMapping("testFile")
    public String postFile() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file",new FileSystemResource("D://a.txt"));
        map.add("name","张三");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        return restTemplate.postForObject(testUrl + "/testFile",new HttpEntity<>(map,httpHeaders),String.class);
    }

}
