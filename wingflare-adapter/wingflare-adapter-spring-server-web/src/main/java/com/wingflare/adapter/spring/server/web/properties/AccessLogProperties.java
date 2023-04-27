package com.wingflare.adapter.spring.server.web.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 应用内AccessLog配置
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Configuration
@ConfigurationProperties(prefix = "wf.accesslog")
public class AccessLogProperties {

    private Boolean enable = false;

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
