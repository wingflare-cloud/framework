package com.wingflare.engine.task.server.web.model.event;

import com.wingflare.engine.task.server.common.enums.WebSocketSceneEnum;
import org.springframework.context.ApplicationEvent;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.model.request
 * @Project：snail-job
 * @Date：2025/3/5 16:54
 * @Filename：WebSocketRequestVO
 * @since 1.5.0
 */
public class WsRequestEvent extends ApplicationEvent {
    /**
     * wb类型
     */
    private String sid;

    /**
     * context
     */
    private String message;

    private WebSocketSceneEnum sceneEnum;

    public WsRequestEvent(Object source) {
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

    public WebSocketSceneEnum getSceneEnum() {
        return sceneEnum;
    }

    public void setSceneEnum(WebSocketSceneEnum sceneEnum) {
        this.sceneEnum = sceneEnum;
    }
}
