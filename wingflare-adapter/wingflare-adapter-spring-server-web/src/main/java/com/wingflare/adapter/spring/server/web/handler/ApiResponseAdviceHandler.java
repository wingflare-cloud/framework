package com.wingflare.adapter.spring.server.web.handler;


import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.spring.ResponseConverter;
import com.wingflare.lib.standard.utils.OrderUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;


/**
 * 接口统一返回数据格式处理器
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@RestControllerAdvice
public class ApiResponseAdviceHandler implements ResponseBodyAdvice<Object>, ApplicationContextAware
{

    public Collection<ResponseConverter> converters;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object o,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse
    ) {
        if (CollectionUtil.isEmpty(converters)) {
            return o;
        }

        for (ResponseConverter converter : converters) {
            o = converter.convert(o);
        }

        return o;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        converters = OrderUtil.asc(applicationContext.getBeansOfType(ResponseConverter.class)
                .values());
    }
}
