package com.wingflare.adapter.spring.common.feign;


import feign.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * feign日志
 * 2022-11-09
 * ycx
 */
public class InfoFeignLoggerFactory implements FeignLoggerFactory {

    @Override
    public Logger create(Class<?> type) {
        return new InfoFeignLogger(LoggerFactory.getLogger(type));
    }

}
