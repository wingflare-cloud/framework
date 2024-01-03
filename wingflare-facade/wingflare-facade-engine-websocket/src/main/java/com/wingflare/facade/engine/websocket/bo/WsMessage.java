package com.wingflare.facade.engine.websocket.bo;

import java.util.List;

/**
 * @author naizui_ycx
 * @className WsMessage
 * @email chenxi2511@qqq.com
 * @date 2024/01/03
 * @description ws消息
 */
public class WsMessage<T> {

    /**
     * 终端名
     */
    private List<String> names;

    /**
     * 终端
     */
    private List<Terminal> terminals;

    /**
     * 频道
     */
    private List<String> topics;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 消息体
     */
    private T body;


    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
