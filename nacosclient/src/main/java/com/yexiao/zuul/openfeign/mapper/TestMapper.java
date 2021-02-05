package com.yexiao.zuul.openfeign.mapper;


import com.yexiao.zuul.openfeign.hystrix.TestCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xuhf
 * @date 2021/2/1 14:51
 **/
@FeignClient(value = "provider",fallback = TestCallback.class)
public interface TestMapper {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable("name") String name);

    @GetMapping("/get?name={name}")
    String get(@PathVariable("name") String name);

    @PostMapping("/testForm")
    String testForm(@RequestParam Map<String,Object> map);

    @PostMapping("/testJson")
    String testJson(Map<String,Object> map);

    @PostMapping(value = "/testFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String testFile(MultiValueMap<String,Object> map);



}
