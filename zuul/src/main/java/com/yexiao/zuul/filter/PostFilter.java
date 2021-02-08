package com.yexiao.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.IoUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.conn.EofSensorInputStream;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import sun.net.www.http.ChunkedInputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author xuhf
 * @date 2021/2/7 9:41
 **/
@Component
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        try {
            Object o = currentContext.get("zuulResponse");
            MediaType contentType = null;
            if(o != null && o instanceof RibbonHttpResponse){
                contentType = ((RibbonHttpResponse) o).getHeaders().getContentType();
            }
            if(contentType != null && contentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
                InputStream bufferedInputStream = currentContext.getResponseDataStream();


                String s = IOUtils.toString(bufferedInputStream);
                JSONObject jsonObject = JSONObject.parseObject(s);
                String code = jsonObject.getString("code");
                if (code != null && code != "") {
                    currentContext.setResponseStatusCode(Integer.parseInt(code));
                }
                currentContext.getResponse().getOutputStream().write(s.getBytes(Consts.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
