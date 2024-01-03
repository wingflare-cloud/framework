package com.wingflare.engine.websocket.controller.http;

import com.wingflare.facade.engine.websocket.bo.WsMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author naizui_ycx
 * @className MessageController
 * @email chenxi2511@qqq.com
 * @date 2024/01/03
 * @description 消息相关
 */
@RestController
@RequestMapping("/open/api/message")
public class MessageController {

    /**
     * 发送消息
     *
     * @param wsMessage
     * @return
     */
    @RequestMapping(value = "/sendTo", method = RequestMethod.POST)
    public String sendTo(WsMessage wsMessage) {
        return "";
    }

}
