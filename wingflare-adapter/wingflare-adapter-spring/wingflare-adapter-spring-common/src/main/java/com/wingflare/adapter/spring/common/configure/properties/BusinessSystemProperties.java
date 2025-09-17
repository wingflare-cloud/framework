package com.wingflare.adapter.spring.common.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author naizui_ycx
 * @date {2022/2/9}
 * @description 业务系统配置
 */
@Configuration
@ConfigurationProperties(prefix = "system")
public class BusinessSystemProperties extends com.wingflare.lib.standard.properties.BusinessSystemProperties {

}
