package com.wingflare.lib.spring.utils;


import com.wingflare.lib.standard.Event;
import com.wingflare.lib.standard.model.EventCtx;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author naizui_ycx
 * @date {2022/1/4}
 * @description 事件工具
 */
@Component
@ConditionalOnMissingBean(com.wingflare.lib.standard.EventUtil.class)
public class EventUtil implements com.wingflare.lib.standard.EventUtil {

    @Override
    public Event publishEvent(String eventName) {
        return publishEvent(new com.wingflare.lib.spring.event.Event(eventName));
    }

    @Override
    public Event publishEvent(String eventName, Object source) {
        return publishEvent(new com.wingflare.lib.spring.event.Event(eventName, source));
    }

    @Override
    public Event publishEvent(String eventName, Object source, Object evData) {
        return publishEvent(new com.wingflare.lib.spring.event.Event(eventName, source, evData));
    }

    @Override
    public Event publishEvent(String eventName, Object source, Object evData, String evStatus) {
        return publishEvent(new com.wingflare.lib.spring.event.Event(eventName, source, evData, evStatus));
    }

    @Override
    public Event publishEvent(String eventName, Object source, Object evData, String evStatus, String evVersion) {
        return publishEvent(new com.wingflare.lib.spring.event.Event(eventName, source, evData, evStatus, evVersion));
    }

    @Override
    public Event publishEvent(Event event) {
        EventCtx eventCtx = EventCtx.getInstance();
        eventCtx.setEvent(event);
        SpringUtil.getApplicationContext().publishEvent(event);
        return event;
    }

}
