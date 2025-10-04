package com.wingflare.engine.task.client.spring.starter.log.report;


import com.wingflare.engine.task.client.common.log.support.TaskLogManager;
import com.wingflare.engine.task.client.core.log.JobLogMeta;
import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.request.JobLogTaskRequest;


/**
 * @author xiaowoniu
 * @date 2024-03-20 23:25:24
 * @since 3.2.0
 */
public class JobLogReport extends AbstractLogReport<JobLogTaskRequest> {

    @Override
    public boolean supports() {
        return LogTypeEnum.JOB == TaskLogManager.getLogType();
    }

    @Override
    protected JobLogTaskRequest buildLogTaskDTO(LogContentDTO logContentDTO) {
        JobLogMeta context = (JobLogMeta) TaskLogManager.getLogMeta();
        JobLogTaskRequest logTaskDTO = new JobLogTaskRequest();
        logTaskDTO.setJobId(context.getJobId());
        logTaskDTO.setLogType(LogTypeEnum.JOB.name());
        logTaskDTO.setTaskId(context.getTaskId());
        logTaskDTO.setTaskBatchId(context.getTaskBatchId());
        logTaskDTO.setRealTime(logContentDTO.getTimeStamp());
        logTaskDTO.setNamespaceId(context.getNamespaceId());
        logTaskDTO.setGroupName(context.getGroupName());
        logTaskDTO.setFieldList(logContentDTO.getFieldList());
        return logTaskDTO;
    }
}
