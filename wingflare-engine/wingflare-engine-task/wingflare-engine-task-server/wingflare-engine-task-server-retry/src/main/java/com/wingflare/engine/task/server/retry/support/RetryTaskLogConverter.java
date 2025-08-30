package com.wingflare.engine.task.server.retry.support;

import com.wingflare.engine.task.server.common.dto.RetryLogMetaDTO;
import com.wingflare.engine.task.server.retry.dto.RequestCallbackExecutorDTO;
import com.wingflare.engine.task.server.retry.dto.RequestRetryExecutorDTO;
import com.wingflare.engine.task.server.retry.dto.RetryMergePartitionTaskDTO;
import com.wingflare.engine.task.server.retry.dto.RetryTaskLogDTO;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import com.wingflare.task.datasource.template.persistence.po.RetryTask;
import com.wingflare.task.datasource.template.persistence.po.RetryTaskLogMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2023-05-05 16:15
 */
@Mapper
public interface RetryTaskLogConverter {

    RetryTaskLogConverter INSTANCE = Mappers.getMapper(RetryTaskLogConverter.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    RetryTask toRetryTask(Retry retry);

    RetryTaskLogDTO toRetryTaskLogDTO(Retry retry);

    RetryTaskLogDTO toRetryTaskLogDTO(RequestRetryExecutorDTO retry);

    List<RetryMergePartitionTaskDTO> toRetryMergePartitionTaskDTOs(List<RetryTask> retryTaskList);

    RetryTaskLogMessage toRetryTaskLogMessage(RetryTaskLogMessage message);

    RetryLogMetaDTO toRetryLogMetaDTO(RequestRetryExecutorDTO executorDTO);

    RetryLogMetaDTO toRetryLogMetaDTO(RequestCallbackExecutorDTO executorDTO);
}
