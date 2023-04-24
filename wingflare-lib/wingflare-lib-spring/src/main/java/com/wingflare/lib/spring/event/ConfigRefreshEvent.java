package com.wingflare.lib.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author naizui_ycx
 * @date {2022/3/6}
 * @description
 */
public class ConfigRefreshEvent extends ApplicationEvent {

    private String key;
    private Object beforeRefresh;
    private Object afterRefresh;

    public ConfigRefreshEvent(Object source, String key, Object beforeRefresh, Object afterRefresh) {
        super(source);
        this.key = key;
        this.beforeRefresh = beforeRefresh;
        this.afterRefresh = afterRefresh;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getBeforeRefresh() {
        return beforeRefresh;
    }

    public void setBeforeRefresh(String beforeRefresh) {
        this.beforeRefresh = beforeRefresh;
    }

    public Object getAfterRefresh() {
        return afterRefresh;
    }

    public void setAfterRefresh(String afterRefresh) {
        this.afterRefresh = afterRefresh;
    }

}
