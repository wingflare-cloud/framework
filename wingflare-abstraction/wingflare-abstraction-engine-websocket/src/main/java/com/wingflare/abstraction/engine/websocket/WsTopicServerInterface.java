package com.wingflare.abstraction.engine.websocket;

import com.wingflare.facade.engine.websocket.bo.BindTopic;

import java.util.List;

/**
 * @author naizui_ycx
 * @interfaceName WsAuthInterface
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description ws频道操作
 */
public interface WsTopicServerInterface {

    /**
     * 终端绑定频道
     *
     * @param bindTopic
     */
    public void bindTopic(BindTopic bindTopic);

    /**
     * 终端解绑频道
     *
     * @param terminalSn
     * @param topics
     */
    public void unbindTopic(String terminalSn, List<String> topics);

}
