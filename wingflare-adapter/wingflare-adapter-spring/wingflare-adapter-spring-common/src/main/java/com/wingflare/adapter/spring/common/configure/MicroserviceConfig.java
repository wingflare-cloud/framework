package com.wingflare.adapter.spring.common.configure;


import com.wingflare.adapter.spring.common.annotation.ConditionalOnAnnotationPresent;
import com.wingflare.adapter.spring.common.aspect.InternalApiAuthAspect;
import com.wingflare.adapter.spring.common.aspect.OriginalRespAspect;
import com.wingflare.adapter.spring.common.configure.properties.ApiProperties;
import com.wingflare.api.core.annotation.MicroserviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微服务模块配置
 */
@Configuration
public class MicroserviceConfig {

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public ApiProperties apiProperties() {
        return new ApiProperties();
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public OriginalRespAspect regOriginalRespAspect() {
        return new OriginalRespAspect();
    }

    @Bean
    @ConditionalOnAnnotationPresent(MicroserviceMode.class)
    public InternalApiAuthAspect regInternalApiAuthAspect() {
        return new InternalApiAuthAspect();
    }

}
