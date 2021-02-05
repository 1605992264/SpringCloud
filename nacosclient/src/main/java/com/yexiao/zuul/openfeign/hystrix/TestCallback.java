package com.yexiao.zuul.openfeign.hystrix;

import com.yexiao.zuul.openfeign.mapper.TestMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author xuhf
 * @date 2021/2/5 13:54
 **/
@Component
public class TestCallback implements TestMapper {

    @Override
    public String hello(String name) {
        return null;
    }

    @Override
    public String get(String name) {
        return "失败";
    }

    @Override
    public String testForm(Map<String, Object> map) {
        return null;
    }

    @Override
    public String testJson(Map<String, Object> map) {
        return null;
    }

    @Override
    public String testFile(MultiValueMap<String, Object> map) {
        return null;
    }

}
