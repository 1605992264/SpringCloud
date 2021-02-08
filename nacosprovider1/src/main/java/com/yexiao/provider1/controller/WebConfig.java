package com.yexiao.provider1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author xuhf
 * @date 2020/8/26 15:36
 * 拦截配置
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截处理器
     *  */
    @Autowired
    private DefaultHandlerInterceptor handlerInterceptor(){
        return new DefaultHandlerInterceptor();
    }


    /***
     * 添加链式处理器
     * 以添加拦截顺序来执行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(handlerInterceptor());
        // 设置拦截路径
        interceptorRegistration.addPathPatterns("/**");
    }

    class DefaultHandlerInterceptor implements HandlerInterceptor{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Map<String, String[]> parameterMap = request.getParameterMap();
            return true;
        }
    }


}
