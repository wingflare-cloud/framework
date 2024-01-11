package com.wingflare.facade.engine.websocket.bo;

import java.util.List;
import com.alibaba.fastjson.JSONObject;

/**
 * @author naizui_ycx
 * @className WsMessage
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description ws消息
 */
public class WsMessage<T> {

    /**
     * 终端
     */
    private List<String> terminalSnList;

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
    private String body;


    public List<String> getTerminalSnList() {
        return terminalSnList;
    }

    public void setTerminalSnList(List<String> terminalSnList) {
        this.terminalSnList = terminalSnList;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setContent(T content) {
        setBody(JSONObject.toJSONString(content));
    }
}
