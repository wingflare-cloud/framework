package com.wingflare.lib.captcha;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 验证码配置
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class CaptchaConfig {

    /**
     * 验证码名字
     */
    private String name;

    /**
     * 验证码过期时间
     */
    private long timeout = 60;

    /**
     * 过期时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 验证码类型
     */
    private String captchaType;

    /**
     * 启用验证码的url匹配规则
     */
    private List<String> urlPatterns;

    /**
     * 验证码具体配置项
     */
    private Properties properties;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(List<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
