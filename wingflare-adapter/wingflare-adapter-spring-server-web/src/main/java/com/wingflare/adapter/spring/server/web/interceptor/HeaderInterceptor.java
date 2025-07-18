package com.wingflare.adapter.spring.server.web.interceptor;


import com.wingflare.adapter.spring.server.web.utils.ServletUtil;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.spring.configure.properties.SystemContextProperties;
import com.wingflare.lib.standard.utils.CtxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.*;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @description 内网服务头信息转化
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {


    @Resource
    private SystemContextProperties systemContextProperties;


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
        CtxUtil.cxtSetter(systemContextProperties.getGlobalCtx(), key -> ServletUtil.getHeader(request, key));
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {
        if (ex != null || logger.isDebugEnabled()) {
            Map<String, Object> contextMap = ContextHolder.getLocalMap();

            if (CollectionUtil.isNotEmpty(contextMap)) {
                logger.info("ctx", e(contextMap));
            }
        }

        ContextHolder.remove();
    }

}
