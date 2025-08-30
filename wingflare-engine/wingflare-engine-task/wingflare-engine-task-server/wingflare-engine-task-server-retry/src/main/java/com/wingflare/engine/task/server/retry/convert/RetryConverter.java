package com.wingflare.engine.task.server.retry.convert;

import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-22
 */
@Mapper
public interface RetryConverter {
    RetryConverter INSTANCE = Mappers.getMapper(RetryConverter.class);

    RetryTaskPrepareDTO toRetryTaskPrepareDTO(Retry retry);

    TaskStopJobDTO toTaskStopJobDTO(Retry retry);
}
