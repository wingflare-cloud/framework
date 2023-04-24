package com.wingflare.lib.spring.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author naizui_ycx
 * @date {2022/2/9}
 * @description 业务系统配置
 */
@Configuration
@ConfigurationProperties(prefix = "business.system")
public class BusinessSystemProperties {

    /**
     * 业务系统名，根据系统划分功能以及权限时会根据该配置进行鉴权以及信息读取
     */
    private Set<String> names = new HashSet<>();

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }
}
