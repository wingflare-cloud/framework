package com.wingflare.engine.task.server.retry.support.log;

import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.log.dto.TaskLogFieldDTO;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.server.common.LogStorage;
import com.wingflare.engine.task.server.common.dto.LogMetaDTO;
import com.wingflare.engine.task.server.common.dto.RetryLogMetaDTO;
import com.wingflare.engine.task.server.common.log.LogStorageFactory;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RetryTaskLogDTO;
import com.google.common.collect.Lists;
import org.apache.pekko.actor.ActorRef;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: xiaowoniu
 * @date : 2024-03-22
 * @since : 3.2.0
 */
@Component
public class RetryLogStorage implements LogStorage, InitializingBean {

    @Override
    public LogTypeEnum logType() {
        return LogTypeEnum.RETRY;
    }

    @Override
    public void storage(final LogContentDTO logContentDTO, final LogMetaDTO logMetaDTO) {
        RetryLogMetaDTO retryLogMetaDTO = (RetryLogMetaDTO) logMetaDTO;
        RetryTaskLogDTO retryTaskLogDTO = new RetryTaskLogDTO();
        Map<String, String> messageMap = logContentDTO.getFieldList()
                .stream()
                .filter(logTaskDTO_ -> !Objects.isNull(logTaskDTO_.getValue()))
                .collect(Collectors.toMap(TaskLogFieldDTO::getName, TaskLogFieldDTO::getValue));
        retryTaskLogDTO.setMessage(JsonUtil.toJsonString(Lists.newArrayList(messageMap)));
        retryTaskLogDTO.setGroupName(retryLogMetaDTO.getGroupName());
        retryTaskLogDTO.setNamespaceId(retryLogMetaDTO.getNamespaceId());
        retryTaskLogDTO.setRetryId(retryLogMetaDTO.getRetryId());
        retryTaskLogDTO.setRetryTaskId(retryLogMetaDTO.getRetryTaskId());
        retryTaskLogDTO.setRealTime(retryLogMetaDTO.getTimestamp());
        ActorRef actorRef = ActorGenerator.logActor();
        actorRef.tell(retryTaskLogDTO, actorRef);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogStorageFactory.register(logType(), this);
    }
}
