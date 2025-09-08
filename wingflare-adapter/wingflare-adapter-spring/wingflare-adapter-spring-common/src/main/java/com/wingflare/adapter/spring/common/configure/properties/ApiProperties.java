package com.wingflare.adapter.spring.common.configure.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * api注解包配置
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Configuration
@ConfigurationProperties(prefix = "wf.api")
public class ApiProperties {

    /**
     * 注解类名
     */
    private List<String> annotationClasses = new ArrayList<String>(){{
        add("org.springframework.cloud.openfeign.FeignClient");
        add("com.wingflare.lib.spring.annotation.ApiClient");
    }};

    /**
     * 基础类名
     */
    private List<String> baseClasses = new ArrayList<String>(){{
    }};

    public List<String> getAnnotationClasses() {
        return annotationClasses;
    }

    public void setAnnotationClasses(List<String> annotationClasses) {
        this.annotationClasses = annotationClasses;
    }

    public List<String> getBaseClasses() {
        return baseClasses;
    }

    public void setBaseClasses(List<String> baseClasses) {
        this.baseClasses = baseClasses;
    }
}
