package com.wingflare.engine.websocket.controller.websocket;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author naizui_ycx
 * @className CommonWs
 * @email chenxi2511@qqq.com
 * @date 2024/01/03
 * @description 通用ws控制器
 */
@Controller
public class CommonWs {

    @MessageExceptionHandler
    @SendToUser("/t/errors")
    public Exception handleExceptions(Exception t) {
        t.printStackTrace();
        return t;
    }

}
