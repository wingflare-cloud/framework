package com.wingflare.engine.task.client.core.log;

import com.wingflare.engine.task.client.common.log.report.AbstractLogReport;
import com.wingflare.engine.task.client.common.log.support.SnailJobLogManager;
import com.wingflare.engine.task.common.log.dto.LogContentDTO;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.request.JobLogTaskRequest;
import org.springframework.stereotype.Component;

/**
 * @author xiaowoniu
 * @date 2024-03-20 23:25:24
 * @since 3.2.0
 */
@Component
public class JobLogReport extends AbstractLogReport<JobLogTaskRequest> {

    @Override
    public boolean supports() {
        return LogTypeEnum.JOB == SnailJobLogManager.getLogType();
    }

    @Override
    protected JobLogTaskRequest buildLogTaskDTO(LogContentDTO logContentDTO) {
        JobLogMeta context = (JobLogMeta) SnailJobLogManager.getLogMeta();
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
