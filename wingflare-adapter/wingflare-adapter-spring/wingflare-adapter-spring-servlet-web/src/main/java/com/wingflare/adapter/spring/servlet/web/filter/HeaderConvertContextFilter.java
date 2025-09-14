package com.wingflare.adapter.spring.servlet.web.filter;


import com.wingflare.adapter.spring.common.configure.properties.SystemContextProperties;
import com.wingflare.adapter.spring.servlet.web.utils.ServletUtil;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.SerializationUtil;
import com.wingflare.lib.core.utils.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @description 内网服务头信息转化
 */
public class HeaderConvertContextFilter implements Filter, Ordered {

    @Resource
    private SystemContextProperties systemContextProperties;


    private final Logger logger = LoggerFactory.getLogger(HeaderConvertContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest request) {
            if (CollectionUtil.isNotEmpty(systemContextProperties.getGlobalCtx())) {
                cxtSetter(systemContextProperties.getGlobalCtx(),
                        key -> ServletUtil.getHeader(request, key));
            }
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (logger.isDebugEnabled()) {
                Map<String, Object> contextMap = ContextHolder.getLocalMap();

                if (CollectionUtil.isNotEmpty(contextMap)) {
                    logger.debug("ctx", e(contextMap));
                }
            }

            ContextHolder.remove();
        }
    }

    public static void cxtSetter(Map<String, String> keys, Function<String, String> function) {
        for (Map.Entry<String, String> key : keys.entrySet()) {
            String value = function.apply(key.getKey());
            if (StringUtil.isNotEmpty(value)) {
                String name;
                if (StringUtil.isNotBlank(key.getValue())) {
                    name = key.getValue();
                } else {
                    name = StringUtil.toCamelCase(key.getKey(), '-');
                }

                Matcher mat = SerializationUtil.typeValueMatch(value);

                if (mat.find()) {
                    ContextHolder.set(name, SerializationUtil.typeValueDecode(mat.group(1)));
                } else {
                    ContextHolder.set(name, value);
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}