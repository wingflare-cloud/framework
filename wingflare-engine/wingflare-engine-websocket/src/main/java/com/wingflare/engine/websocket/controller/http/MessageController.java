package com.wingflare.engine.websocket.controller.http;

import com.wingflare.facade.engine.websocket.bo.MessageSendBo;
import com.wingflare.facade.engine.websocket.bo.WsMsg;
import com.wingflare.lib.core.Builder;
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
     * @param messageSendBo
     * @return
     */
    @RequestMapping(value = "/sendTo", method = RequestMethod.POST)
    public R<Boolean> sendTo(MessageSendBo messageSendBo) {

        WsMsg wsMsg = Builder.of(WsMsg::new)
                .with(WsMsg::setMsgId, messageSendBo.getMsgId())
                .with(WsMsg::setBody, messageSendBo.getBody())
                .build();

        if (CollectionUtil.isNotEmpty(messageSendBo.getTerminalSnList())) {
            messageSendBo.getTerminalSnList().forEach(sn -> {

            });
        }

        return R.ok(true);
    }

}
