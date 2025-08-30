package com.wingflare.engine.task.server.retry.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.dto.TaskLogFieldDTO;
import com.wingflare.engine.task.common.model.request.RetryLogTaskRequest;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RetryTaskLogDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
import org.apache.pekko.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 处理日志信息
 *
 * @author: opensnail
 * @date : 2023-06-16 11:33
 * @since 2.0.0
 */
@Component(ActorGenerator.LOG_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RetryLogActor extends AbstractActor {
    private final AccessTemplate accessTemplate;

    public RetryLogActor(AccessTemplate accessTemplate) {
        this.accessTemplate = accessTemplate;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(List.class,
                list -> {
                    if (CollUtil.isEmpty(list)) {
                        return;
                    }
                    saveRetryTaskLogMessage((List<RetryLogTaskRequest>) list);
                    getContext().stop(getSelf());
                }).match(RetryTaskLogDTO.class,
                retryTaskLogDTO -> {
                    saveRetryTaskLogMessage(retryTaskLogDTO);
                    getContext().stop(getSelf());
                }).build();
    }

    private void saveRetryTaskLogMessage(final List<RetryLogTaskRequest> list) {

        List<RetryLogTaskRequest> jobLogTasks = list;
        Map<Long, List<RetryLogTaskRequest>> logTaskDTOMap = jobLogTasks.
                stream().collect(Collectors.groupingBy(RetryLogTaskRequest::getRetryTaskId, Collectors.toList()));
        List<RetryTaskLogMessageDO> retryTaskLogMessages = new ArrayList<>();
        for (List<RetryLogTaskRequest> logTaskDTOList : logTaskDTOMap.values()) {
            RetryTaskLogMessageDO retryTaskLogMessage = RetryTaskConverter.INSTANCE.toRetryTaskLogMessage(
                    logTaskDTOList.get(0));
            retryTaskLogMessage.setCreateDt(LocalDateTime.now());
            retryTaskLogMessage.setLogNum(logTaskDTOList.size());
            List<Map<String, String>> messageMapList = logTaskDTOList.stream()
                    .map(taskDTO -> taskDTO.getFieldList()
                            .stream().filter(logTaskDTO_ -> !Objects.isNull(logTaskDTO_.getValue()))
                            .collect(Collectors.toMap(TaskLogFieldDTO::getName, TaskLogFieldDTO::getValue)))
                    .collect(Collectors.toList());
            retryTaskLogMessage.setMessage(JsonUtil.toJsonString(messageMapList));

            retryTaskLogMessages.add(retryTaskLogMessage);
        }

        accessTemplate.getRetryTaskLogMessageAccess().insertBatch(retryTaskLogMessages);
    }

    /**
     * 报错日志详情
     */
    private void saveRetryTaskLogMessage(final RetryTaskLogDTO retryTaskLogDTO) {

        // 记录重试日志
        RetryTaskLogMessageDO retryTaskLogMessage = new RetryTaskLogMessageDO();
        retryTaskLogMessage.setRetryId(retryTaskLogDTO.getRetryId());
        retryTaskLogMessage.setRetryTaskId(retryTaskLogDTO.getRetryTaskId());
        retryTaskLogMessage.setGroupName(retryTaskLogDTO.getGroupName());
        retryTaskLogMessage.setNamespaceId(retryTaskLogDTO.getNamespaceId());
        retryTaskLogMessage.setLogNum(1);
        retryTaskLogMessage.setRealTime(retryTaskLogDTO.getRealTime());
        String errorMessage = retryTaskLogDTO.getMessage();
        retryTaskLogMessage.setMessage(
                StrUtil.isBlank(errorMessage) ? StrUtil.EMPTY : errorMessage);
        retryTaskLogMessage.setCreateDt(Optional.ofNullable(retryTaskLogDTO.getTriggerTime()).orElse(LocalDateTime.now()));
        accessTemplate.getRetryTaskLogMessageAccess().insert(retryTaskLogMessage);

    }
}
