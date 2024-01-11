package com.wingflare.engine.websocket.controller.http;

import com.wingflare.engine.websocket.db.WsWebhookDo;
import com.wingflare.engine.websocket.service.WsWebhookServer;
import com.wingflare.facade.engine.websocket.bo.CallbackServerInfo;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.standard.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @className WebhookController
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description webhook相关开放接口
 */

@RestController
@RequestMapping("/open/api/webhook")
public class WebhookController {

    @Resource
    private WsWebhookServer webhookServer;

    /**
     * 注册webhook回调服务
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public R<Boolean> regWebhook(CallbackServerInfo info) {
        WsWebhookDo webhookDo = Builder.of(WsWebhookDo::new)
                .with(WsWebhookDo::setTopic, info.getTopic())
                .with(WsWebhookDo::setEnableSsl, info.isEnableSSL() ? 0 : 1)
                .with(WsWebhookDo::setHost, info.getHost())
                .with(WsWebhookDo::setServerName, info.getServerName())
                .with(WsWebhookDo::setPath, info.getPath())
                .build();

        WsWebhookDo old = webhookServer.getOne(
                webhookServer.lambdaQuery()
                        .eq(WsWebhookDo::getServerName, info.getServerName())
                        .eq(WsWebhookDo::getTopic, info.getTopic())
        );

        if (old == null) {
            webhookServer.save(webhookDo);
        } else {
            old.setOnNew(webhookDo);
            webhookServer.updateById(old);
        }

        return R.ok(true);
    }

    /**
     * 注册回调服务
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public R<Boolean> removeWebhook(CallbackServerInfo info) {
        webhookServer.remove(
                webhookServer.lambdaQuery()
                        .eq(WsWebhookDo::getServerName, info.getServerName())
                        .eq(WsWebhookDo::getTopic, info.getTopic())
        );
        return R.ok(true);
    }

}
