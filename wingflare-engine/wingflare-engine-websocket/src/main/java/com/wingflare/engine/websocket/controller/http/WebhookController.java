package com.wingflare.engine.websocket.controller.http;

import com.wingflare.facade.engine.websocket.bo.CallbackServerInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 注册webhook回调服务
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String regWebhook(CallbackServerInfo info) {
        return "success";
    }

    /**
     * 注册回调服务
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeWebhook(CallbackServerInfo info) {
        return "success";
    }

}
