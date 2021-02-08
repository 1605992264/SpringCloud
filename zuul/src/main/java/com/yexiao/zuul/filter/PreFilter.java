package com.yexiao.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.ResponseContent;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2021/2/5 16:13
 **/
@Component
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        currentContext.setSendZuulResponse(false);
//        currentContext.setResponseStatusCode(429);
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
