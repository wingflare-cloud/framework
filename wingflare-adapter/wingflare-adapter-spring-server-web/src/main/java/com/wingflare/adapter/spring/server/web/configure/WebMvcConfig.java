package com.wingflare.adapter.spring.server.web.configure;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.adapter.spring.server.web.interceptor.HeaderInterceptor;
import com.wingflare.lib.spring.converter.StringOrMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Author naizui_ycx
 * @email chenxi2511@qq.com
 * @Description web配置
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private HeaderInterceptor headerInterceptor;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .order(-10);
    }

    /**
     * 自定义请求头拦截器
     */
    public HeaderInterceptor getHeaderInterceptor() {
        return headerInterceptor;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new StringOrMappingJackson2HttpMessageConverter(objectMapper));
    }

}
