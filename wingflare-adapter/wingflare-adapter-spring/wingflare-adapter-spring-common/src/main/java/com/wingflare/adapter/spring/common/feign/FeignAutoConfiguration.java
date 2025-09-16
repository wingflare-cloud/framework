package com.wingflare.adapter.spring.common.feign;


import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

/**
 * @ClassName FeignAutoConfiguration
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description feign配置
 */
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignAutoConfiguration {

    private final ObjectFactory<HttpMessageConverters> httpMessageConvertersObjectFactory;

    public FeignAutoConfiguration(ObjectFactory<HttpMessageConverters> httpMessageConvertersObjectFactory) {
        this.httpMessageConvertersObjectFactory = httpMessageConvertersObjectFactory;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @Bean
    public ErrorDecoder feignErrorDecider() {
        return new FeignErrorDecoder();
    }

    @Bean
    public Encoder multipartFormEncoder() {
        List<HttpMessageConverter<?>> converters = this.httpMessageConvertersObjectFactory.getObject().getConverters();
        return new SpringFormEncoder(new SpringEncoder(
                () -> new HttpMessageConverters(converters)));
    }

    @Bean
    public Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignLoggerFactory infoFeignLoggerFactory() {
        return new InfoFeignLoggerFactory();
    }

}
