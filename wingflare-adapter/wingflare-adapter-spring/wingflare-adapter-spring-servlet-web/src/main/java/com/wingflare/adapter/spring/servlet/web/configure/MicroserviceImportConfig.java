package com.wingflare.adapter.spring.servlet.web.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wingflare.adapter.spring.common.annotation.ConditionalOnAnnotationPresent;
import com.wingflare.adapter.spring.common.configure.properties.SystemContextProperties;
import com.wingflare.adapter.spring.servlet.web.SpringServletHttpContainer;
import com.wingflare.adapter.spring.servlet.web.WebCtxSource;
import com.wingflare.adapter.spring.servlet.web.filter.HeaderConvertContextFilter;
import com.wingflare.adapter.spring.servlet.web.handler.ApiResponseAdviceHandler;
import com.wingflare.adapter.spring.servlet.web.handler.WebApiExceptionHandler;
import com.wingflare.api.core.annotation.MicroserviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 微服务导包配置
 */
@Configuration
public class MicroserviceImportConfig {

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public WebMvcConfig webMvcConfig(ObjectMapper objectMapper) {
        return new WebMvcConfig(objectMapper);
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public ApiResponseAdviceHandler apiResponseAdviceHandler() {
        return new ApiResponseAdviceHandler();
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public WebApiExceptionHandler webApiExceptionHandler(ObjectMapper objectMapper) {
        return new WebApiExceptionHandler(objectMapper);
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public HeaderConvertContextFilter headerConvertContextFilter(SystemContextProperties systemContextProperties,
                                                                 SpringServletHttpContainer container) {
        return new HeaderConvertContextFilter(systemContextProperties, container);
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public WebCtxSource webCtxSource(SpringServletHttpContainer container) {
        return new WebCtxSource(container);
    }

}
