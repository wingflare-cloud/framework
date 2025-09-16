package com.wingflare.adapter.spring.servlet.web.configure;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.adapter.spring.common.converter.StringOrMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Author naizui_ycx
 * @email chenxi2511@qq.com
 * @Description web配置
 */
public class WebMvcConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public WebMvcConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new StringOrMappingJackson2HttpMessageConverter(objectMapper));
    }

}