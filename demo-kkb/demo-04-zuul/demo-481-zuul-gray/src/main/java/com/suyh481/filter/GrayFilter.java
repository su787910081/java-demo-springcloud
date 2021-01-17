package com.suyh481.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 第一种方式，处理灰度发布
 */
// @Component
public class GrayFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        // 所有请求都通过zuul过滤
        return true;
    }

    /**
     * 这里，通过请求客户端中的请求头中的"gray-mark" 的值来判断当前请求是否走灰度实例访问请求
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 获取指定的请求头信息，该头信息在浏览器提交请求时携带，用于区分该请求要被路由到哪个主机处理
        String mark = request.getHeader("gray-mark");
        // 默认将请求路由到running-host上
        // 这里的"running-host" 是在访问微服务中定义的Eureka 元数据的值。
        // 对应的key为: "host-mark"
        // 可以查看微服务"demo-consumer-gray" 的配置项
        RibbonFilterContextHolder.getCurrentContext().add("host-mark", "running-host");
        // 若mark的值不为空且值为enable，则将请求路由到gray-host，其它请求会路由到默认的running-host
        if (!StringUtils.isEmpty(mark) && "enable".equals(mark)) {
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "gray-host");
        }
        return null;
    }
}
