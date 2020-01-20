package com.whstone.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.whstone.common.feign.RedisClient;
import com.whstone.common.constant.RedisConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

    private Logger logger= LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    private RedisClient redisClient;

    @Override
    public String filterType() {
        return "pre";//前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;//优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        //通过对过滤器过滤规则进行修改，在是否需要过滤方法体中，增加 如果请求类型为OPTIONS ，则不进行用户校验，直接跳过该过滤，由框架本身去响应该请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURL().toString();
        if (request.getMethod().equals("OPTIONS")) {
            return false;
        }
        if(url.contains("/api/user/login")){
            return false;
        }
        if(url.contains("/api/user/logout")){
            return false;
        }
        if(url.contains("/api/user/validate")){
            return false;
        }
        return true;//是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String AuthName = request.getHeader("token");
        logger.info("token:" + AuthName);

        if(StringUtils.isBlank(AuthName)){
            //没有加认证token 就没有访问权限
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody("{\"code\":20005,\"msg\":\"重新登录！\"}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }
        //缓存获取
        String hasKey = redisClient.get(RedisConstant.XFEY_LOGIN_TOKEN + AuthName);
        //token过期要求重新授权
        if(StringUtils.isBlank(hasKey)){
            //token失效了
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody("{\"code\":20005,\"msg\":\"令牌失效,需要重新登录！\"}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }

        //*******************结束拦截****************************
        ctx.addZuulRequestHeader("token", AuthName);
        return null;

    }
}
