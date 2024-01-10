package com.wingflare.engine.websocket.biz;

import com.wingflare.engine.websocket.ErrorCode;
import com.wingflare.engine.websocket.PermissionCode;
import com.wingflare.engine.websocket.db.WsTopicBindInfoDo;
import com.wingflare.engine.websocket.service.WsTopicBindInfoServer;
import com.wingflare.facade.engine.websocket.bo.BindTopic;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.utils.CollectionUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class TopicBindBiz {

    @Resource
    private WsTopicBindInfoServer wsTopicBindInfoServer;


    public void bindAll(BindTopic bindTopic) {
        boolean hasReadOnlyTopics = CollectionUtil.isNotEmpty(bindTopic.getReadOnlyTopics());
        boolean hasWriteOnlyTopics = CollectionUtil.isNotEmpty(bindTopic.getWriteOnlyTopics());
        boolean hasTopics = CollectionUtil.isNotEmpty(bindTopic.getTopics());

        if (hasReadOnlyTopics || hasWriteOnlyTopics || hasTopics) {
            List<WsTopicBindInfoDo> wsTopicBindInfoDos = new ArrayList<>();

            if (hasReadOnlyTopics) {
                bindTopic.getReadOnlyTopics().forEach(topic -> wsTopicBindInfoDos.add(Builder.of(WsTopicBindInfoDo::new)
                        .with(WsTopicBindInfoDo::setTerminalSn, bindTopic.getTerminalSn())
                        .with(WsTopicBindInfoDo::setTopic, topic)
                        .with(WsTopicBindInfoDo::setPermissionNum, PermissionCode.READ_ONLY)
                        .build()));
            }

            if (hasWriteOnlyTopics) {
                bindTopic.getWriteOnlyTopics().forEach(topic -> wsTopicBindInfoDos.add(Builder.of(WsTopicBindInfoDo::new)
                        .with(WsTopicBindInfoDo::setTerminalSn, bindTopic.getTerminalSn())
                        .with(WsTopicBindInfoDo::setTopic, topic)
                        .with(WsTopicBindInfoDo::setPermissionNum, PermissionCode.WRITE_ONLY)
                        .build()));
            }

            if (hasTopics) {
                bindTopic.getTopics().forEach(topic -> wsTopicBindInfoDos.add(Builder.of(WsTopicBindInfoDo::new)
                        .with(WsTopicBindInfoDo::setTerminalSn, bindTopic.getTerminalSn())
                        .with(WsTopicBindInfoDo::setTopic, topic)
                        .with(WsTopicBindInfoDo::setPermissionNum, PermissionCode.WRITE_AND_READ)
                        .build()));
            }

            Assert.isTrue(wsTopicBindInfoServer.saveBatch(wsTopicBindInfoDos), ErrorCode.REGISTER_TERMINAL_EXPIRATION);
        }
    }

}
