package com.abc.zuulserver.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
/*
用来进行对member系统的member方法进行限流
 */
@Component
public class LimiterFilter extends ZuulFilter {
    /*
    声明过滤器的类型，可分前置（请求进来，还没处理），后置（处理请求返回再经过过滤器时执行）
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }
    /*
    过滤器执行的顺序，数字越小，越早执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }
    /*
    是否执行过滤器，可以用来对特定系统的路径单独执行，也可以对全部系统执行
     */
    @Override
    public boolean shouldFilter() {
        //获取请求路劲
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //相对路径，没有ip，端口号，只有路径：/order/orderTest
        String uri = request.getRequestURI();
        if("/member/member".equalsIgnoreCase(uri)){
            return true;
        }
        return false;
    }

    /*
    过滤器执行的操作
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //非阻塞式获取令牌
        if(!RATE_LIMITER.tryAcquire()){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
