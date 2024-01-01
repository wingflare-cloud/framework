package com.wingflare.engine.websocket.controller.websocket;


import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * Created by XiuYin.Cui on 2018/5/3.
 * <p>
 * 广播消息
 */
@Controller
public class Broadcast {

    /**
     * 消息异常处理
     *
     * @param t
     * @return
     */
    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public Exception handleExceptions(Exception t) {
        t.printStackTrace();
        return t;
    }


}
