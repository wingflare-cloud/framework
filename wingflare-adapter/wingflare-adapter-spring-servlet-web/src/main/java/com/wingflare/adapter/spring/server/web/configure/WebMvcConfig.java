package com.wingflare.adapter.spring.server.web.configure;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.lib.spring.converter.StringOrMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Author naizui_ycx
 * @email chenxi2511@qq.com
 * @Description web配置
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new StringOrMappingJackson2HttpMessageConverter(objectMapper));
    }

}
