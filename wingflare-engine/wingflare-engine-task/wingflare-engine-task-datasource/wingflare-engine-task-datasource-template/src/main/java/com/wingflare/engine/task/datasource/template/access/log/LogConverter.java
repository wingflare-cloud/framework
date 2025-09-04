package com.wingflare.engine.task.datasource.template.access.log;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.po.JobLogMessage;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTaskLogMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-03-30
 */
@Mapper
public interface LogConverter {

    LogConverter INSTANCE = Mappers.getMapper(LogConverter.class);

    JobLogMessage toJobLogMessage(JobLogMessageDO logMessage);

    List<JobLogMessage> toJobLogMessages(List<JobLogMessageDO> logMessages);

    JobLogMessageDO toJobLogMessageDO(JobLogMessage logMessage);

    List<JobLogMessageDO> toJobLogMessageDOList(List<JobLogMessage> logMessages);

    RetryTaskLogMessage toRetryTaskLogMessage(RetryTaskLogMessageDO logMessage);

    List<RetryTaskLogMessage> toRetryTaskMessages(List<RetryTaskLogMessageDO> logMessages);

    RetryTaskLogMessageDO toRetryTaskLogMessageDO(RetryTaskLogMessage logMessage);

    List<RetryTaskLogMessageDO> toRetryTaskLogMessageDOList(List<RetryTaskLogMessage> logMessages);

}
