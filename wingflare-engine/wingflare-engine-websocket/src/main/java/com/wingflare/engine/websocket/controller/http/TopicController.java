package com.wingflare.engine.websocket.controller.http;

import com.wingflare.abstraction.engine.websocket.WsTopicServerInterface;
import com.wingflare.engine.websocket.ErrorCode;
import com.wingflare.engine.websocket.PermissionCode;
import com.wingflare.engine.websocket.biz.TopicBindBiz;
import com.wingflare.engine.websocket.db.WsTopicBindInfoDo;
import com.wingflare.engine.websocket.service.WsTopicBindInfoServer;
import com.wingflare.facade.engine.websocket.bo.BindTopic;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.standard.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private WsTopicServerInterface wsTopicServerInterface;

    @Resource
    private TopicBindBiz topicBindBiz;

    @Resource
    private WsTopicBindInfoServer wsTopicBindInfoServer;

    /**
     * 频道绑定终端
     *
     * @return
     */
    @RequestMapping(value = "/bind/terminal", method = RequestMethod.POST)
    @Transactional
    public R<Boolean> bindTopic(BindTopic bindTopic) {
        topicBindBiz.bindAll(bindTopic);
        wsTopicServerInterface.bindTopic(bindTopic);
        return R.ok(true);
    }

    /**
     * 频道解绑终端
     *
     * @return
     */
    @RequestMapping(value = "/unbind/terminal", method = RequestMethod.POST)
    public R<Boolean> unbindTopic(BindTopic bindTopic) {
        wsTopicBindInfoServer.remove(wsTopicBindInfoServer.lambdaQuery()
                .eq(WsTopicBindInfoDo::getTerminalSn, bindTopic.getTerminalSn())
                .in(WsTopicBindInfoDo::getTopic, bindTopic.getTopics())
        );
        wsTopicServerInterface.unbindTopic(bindTopic.getTerminalSn(), bindTopic.getTopics());
        return R.ok(true);
    }

}
