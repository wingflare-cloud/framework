package com.wingflare.engine.websocket.controller.http;


import com.wingflare.abstraction.engine.websocket.WsAuthServerInterface;
import com.wingflare.abstraction.engine.websocket.WsTopicServerInterface;
import com.wingflare.engine.websocket.ErrorCode;
import com.wingflare.engine.websocket.biz.TopicBindBiz;
import com.wingflare.engine.websocket.configure.properties.WebSocketProperties;
import com.wingflare.engine.websocket.db.WsSessionDo;
import com.wingflare.engine.websocket.service.WsSessionServer;
import com.wingflare.engine.websocket.utils.WsUtil;
import com.wingflare.facade.engine.websocket.bo.BindTopic;
import com.wingflare.facade.engine.websocket.bo.RegTerminal;
import com.wingflare.facade.engine.websocket.bo.Terminal;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.Hashids;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import com.wingflare.lib.standard.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author naizui_ycx
 * @className OpenApiController
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description 终端相关开放接口
 */

@RestController
@RequestMapping("/open/api/terminal")
public class TerminalController {

    @Resource
    private WsAuthServerInterface wsAuthServer;

    @Resource
    private WebSocketProperties webSocketProperties;

    @Resource
    private WsTopicServerInterface wsTopicServerInterface;

    @Resource
    private SnowflakeUtil snowflakeUtil;

    @Resource
    private WsSessionServer wsSessionServer;

    @Resource
    private TopicBindBiz topicBindBiz;

    private static Hashids hashids = null;

    /**
     * 注册终端
     *
     * @param regTerminal
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @Transactional
    public R<String> regTerminal(RegTerminal regTerminal) {
        WsSessionDo wsSessionDo = Builder.of(WsSessionDo::new)
                .with(WsSessionDo::setTerminalSn, regTerminal.getTerminal().getSn())
                .with(WsSessionDo::setPoint, regTerminal.getTerminal().getPoint())
                .with(WsSessionDo::setSid, getHashids().encode(Hashids.dePack(snowflakeUtil.nextLongId())))
                        .build();

        Assert.isTrue(wsSessionServer.save(wsSessionDo), ErrorCode.REGISTER_TERMINAL_EXPIRATION);

        BindTopic bindTopic = Builder.of(BindTopic::new)
                .with(BindTopic::setReadOnlyTopics, regTerminal.getReadOnlyTopics())
                .with(BindTopic::setWriteOnlyTopics, regTerminal.getWriteOnlyTopics())
                .with(BindTopic::setTopics, regTerminal.getTopics())
                .build();

        topicBindBiz.bindAll(bindTopic);
        wsTopicServerInterface.bindTopic(bindTopic);
        wsAuthServer.regTerminal(wsSessionDo.getSid(), regTerminal.getTerminal());

        return R.ok(wsSessionDo.getSid());
    }

    /**
     * 关闭终端
     *
     * @param terminalSn
     * @return
     */
    @RequestMapping(value = "/close/sn/{terminalSn}", method = RequestMethod.POST)
    @Transactional
    public R<Boolean> closeTerminal(@PathVariable("terminalSn") String terminalSn) {
        try {
            Map<String, Terminal> terminalMap = wsAuthServer.getTerminalBySn(terminalSn);
            if (CollectionUtil.isNotEmpty(terminalMap)) {
                WsUtil.closeSession(terminalMap.keySet().toArray(new String[]{}));
            }
        } catch (IOException e) {
            throw new BusinessLogicException(e.getMessage(), e);
        }

        return R.ok(true);
    }

    /**
     * 关闭终端
     *
     * @return
     */
    @RequestMapping(value = "/close/sid/{sid}", method = RequestMethod.POST)
    @Transactional
    public R<Boolean> closeTerminalBySid(@PathVariable("sid") String sid) {
        try {
            WsUtil.closeSession(sid);
        } catch (IOException e) {
            throw new BusinessLogicException(e.getMessage(), e);
        }

        return R.ok(true);
    }


    private Hashids getHashids() {
        if (hashids == null) {
            hashids = new Hashids(webSocketProperties.getSidSalt());
        }
        return hashids;
    }

}
