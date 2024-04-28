package com.wingflare.facade.engine.websocket.bo;

/**
 * @author naizui_ycx
 * @className WsMessage
 * @email chenxi2511@qq.com
 * @date 2024/01/19
 * @description WsMsg
 */
public class WsMsg {

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 应答消息id
     */
    private String replyMsgId;

    /**
     * 消息内容
     */
    private String body;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReplyMsgId() {
        return replyMsgId;
    }

    public void setReplyMsgId(String replyMsgId) {
        this.replyMsgId = replyMsgId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
