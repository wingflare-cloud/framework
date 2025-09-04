package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.dto.JobLogMessagePartitionTask;
import com.wingflare.engine.task.server.web.model.dto.LogMessagePartitionTask;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
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
public interface LogMessagePartitionTaskConverter {
    LogMessagePartitionTaskConverter INSTANCE = Mappers.getMapper(LogMessagePartitionTaskConverter.class);

    List<LogMessagePartitionTask> toLogMessagePartitionTask(List<RetryTaskLogMessageDO> retryTaskLogMessageList);


    List<JobLogMessagePartitionTask> toJobLogMessagePartitionTasks(List<JobLogMessageDO> jobLogMessageDOS);
}
