package com.wingflare.lib.spring.event;

import com.wingflare.lib.core.utils.StringUtil;
import org.springframework.context.ApplicationEvent;

/**
 * @author naizui_ycx
 * @date {2022/1/4}
 * @description
 */
public class Event extends ApplicationEvent implements com.wingflare.lib.standard.Event {

    /**
     * 事件名称
     */
    protected String eventName;

    /**
     * 事件数据
     */
    protected Object data;

    /**
     * 事件状态
     */
    protected String eventStatus;

    /**
     * 事件版本
     */
    protected String eventVersion;

    /**
     * 事件发生时间
     */
    protected long eventTimestamp;


    public Event(String eventName) {
        this(eventName, false, null);
    }

    public Event(String eventName, Object source) {
        this(eventName, source, null);
    }

    public Event(String eventName, Object source, Object evData) {
        this(eventName, source, evData, null, null);
    }

    public Event(String eventName, Object source, Object evData, String eventStatus) {
        this(eventName, source, evData, eventStatus, null);
    }

    /**
     *
     * @param eventName 事件名
     * @param source 事件原始对象
     * @param evData 事件数据
     * @param eventStatus 事件状态
     * @param eventVersion 事件版本
     */
    public Event(String eventName, Object source, Object evData, String eventStatus, String eventVersion) {
        super(source);

        if (StringUtil.isBlank(eventName)) {
            throw new IllegalArgumentException("Event name cannot be empty");
        }

        setEventName(eventName);
        setData(evData);
        setEventStatus(eventStatus);
        setEventVersion(eventVersion);
        setEventTimestamp(System.currentTimeMillis());
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    private void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getEventStatus() {
        return eventStatus;
    }

    private void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public String getEventVersion() {
        return eventVersion;
    }

    private void setEventVersion(String eventVersion) {
        this.eventVersion = eventVersion;
    }

    @Override
    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
