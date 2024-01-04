package com.wingflare.engine.websocket.controller.http;

import com.wingflare.facade.engine.websocket.bo.BindTopic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author naizui_ycx
 * @className OpenApiController
 * @email chenxi2511@qq.com
 * @date 2024/01/04
 * @description 频道相关开放接口
 */

@RestController
@RequestMapping("/open/api/topic")
public class TopicController {

    /**
     * 频道绑定终端
     *
     * @return
     */
    @RequestMapping(value = "/bind/terminal", method = RequestMethod.POST)
    public String bindTopic(BindTopic bindTopic) {
        return "";
    }

    /**
     * 频道解绑终端
     *
     * @return
     */
    @RequestMapping(value = "/unbind/terminal", method = RequestMethod.POST)
    public String unbindTopic(BindTopic bindTopic) {
        return "";
    }

}
