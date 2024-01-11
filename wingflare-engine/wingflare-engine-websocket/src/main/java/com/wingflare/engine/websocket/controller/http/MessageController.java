package com.wingflare.engine.websocket.controller.http;

import com.wingflare.facade.engine.websocket.bo.WsMessage;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.standard.R;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @className MessageController
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description 消息相关
 */
@RestController
@RequestMapping("/open/api/message")
public class MessageController {

    @Resource
    private SimpMessagingTemplate template;

    /**
     * 发送消息
     *
     * @param wsMessage
     * @return
     */
    @RequestMapping(value = "/sendTo", method = RequestMethod.POST)
    public R<Boolean> sendTo(WsMessage wsMessage) {

        if (CollectionUtil.isNotEmpty(wsMessage.getTerminalSnList())) {
            wsMessage.getTerminalSnList().forEach(sn -> {});
        }

        return R.ok(true);
    }

}
