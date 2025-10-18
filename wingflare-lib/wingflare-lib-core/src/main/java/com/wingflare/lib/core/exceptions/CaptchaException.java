package com.wingflare.lib.core.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证码异常
 */
public class CaptchaException extends RuntimeException {

    private static final long serialVersionUID = 2969128789378454154L;

    private Long expire;

    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
