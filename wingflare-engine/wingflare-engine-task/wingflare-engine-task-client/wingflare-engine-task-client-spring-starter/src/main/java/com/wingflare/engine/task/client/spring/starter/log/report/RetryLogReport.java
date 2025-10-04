package com.wingflare.engine.task.client.spring.starter.log.report;


import com.wingflare.engine.task.client.common.log.support.TaskLogManager;
import com.wingflare.engine.task.client.retry.log.RetryLogMeta;
import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.request.RetryLogTaskRequest;

/**
 * @author xiaowoniu
 * @date 2024-03-20 23:25:24
 * @since 3.2.0
 */
public class RetryLogReport extends AbstractLogReport<RetryLogTaskRequest> {

    @Override
    protected RetryLogTaskRequest buildLogTaskDTO(LogContentDTO logContentDTO) {
        RetryLogMeta context = (RetryLogMeta) TaskLogManager.getLogMeta();
        RetryLogTaskRequest logTaskDTO = new RetryLogTaskRequest();
        logTaskDTO.setLogType(LogTypeEnum.RETRY.name());
        logTaskDTO.setRetryId(context.getRetryId());
        logTaskDTO.setRetryTaskId(context.getRetryTaskId());
        logTaskDTO.setRealTime(logContentDTO.getTimeStamp());
        logTaskDTO.setNamespaceId(context.getNamespaceId());
        logTaskDTO.setGroupName(context.getGroupName());
        logTaskDTO.setFieldList(logContentDTO.getFieldList());
        return logTaskDTO;
    }

    @Override
    public boolean supports() {
        return LogTypeEnum.RETRY == TaskLogManager.getLogType();
    }
}
