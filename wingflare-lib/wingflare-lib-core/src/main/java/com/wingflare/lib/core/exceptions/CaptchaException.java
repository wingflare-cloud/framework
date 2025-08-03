package com.wingflare.lib.core.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证码异常
 */
public class CaptchaException extends RuntimeException {

    private static final long serialVersionUID = 2969128789378454154L;

    public List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
