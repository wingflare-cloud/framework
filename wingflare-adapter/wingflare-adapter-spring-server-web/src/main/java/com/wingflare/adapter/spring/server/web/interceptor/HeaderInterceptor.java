package com.wingflare.adapter.spring.server.web.interceptor;


import com.wingflare.adapter.spring.server.web.utils.ServletUtil;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.spring.configure.properties.SystemInternalProperties;
import com.wingflare.lib.spring.utils.ApiHelperUtil;
import com.wingflare.lib.standard.utils.CtxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @description 内网服务头信息转化
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {


    @Resource
    private SystemInternalProperties systemInternalProperties;


    private final Logger logger = LoggerFactory.getLogger(HeaderInterceptor.class);


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        CtxUtil.cxtSetter(systemInternalProperties.getCtx(), key -> ServletUtil.getHeader(request, key));
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {

        Map<String, Object> contextMap = ContextHolder.getLocalMap();

        if (CollectionUtil.isNotEmpty(contextMap)) {
            StringBuilder builder = new StringBuilder();
            contextMap.forEach((k, v) -> {
                if (!ApiHelperUtil.getSystemCxtKeys().contains(k)) {
                    builder.append(k)
                            .append("=")
                            .append(v)
                            .append("&");
                }
            });

            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
                logger.info("ctx: {}", builder);
            }
        }

        ContextHolder.remove();
    }

}
