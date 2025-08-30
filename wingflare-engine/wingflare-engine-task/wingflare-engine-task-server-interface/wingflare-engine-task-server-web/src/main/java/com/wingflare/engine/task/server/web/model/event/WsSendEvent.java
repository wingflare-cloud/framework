package com.wingflare.engine.task.server.web.model.event;


import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-18
 */
public class WsSendEvent extends ApplicationEvent {

    /**
     * 会话id
     */
    private String sid;

    /**
     * 需要发送的消息
     */
    private String message;

    public WsSendEvent(Object source) {
        super(source);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
