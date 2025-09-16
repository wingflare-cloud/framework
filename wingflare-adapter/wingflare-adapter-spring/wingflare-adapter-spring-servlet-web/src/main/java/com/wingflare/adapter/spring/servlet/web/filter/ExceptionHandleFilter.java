package com.wingflare.adapter.spring.servlet.web.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @description 全局过滤器异常处理
 */
public class ExceptionHandleFilter implements Filter, Ordered {

    private final HandlerExceptionResolver handlerExceptionResolver;

    public ExceptionHandleFilter(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(
                    (HttpServletRequest) servletRequest,
                    (HttpServletResponse) servletResponse,
                    null,
                    e
            );
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}