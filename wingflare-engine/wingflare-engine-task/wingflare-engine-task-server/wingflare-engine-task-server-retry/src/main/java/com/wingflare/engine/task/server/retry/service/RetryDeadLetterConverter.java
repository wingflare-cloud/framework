package com.wingflare.engine.task.server.retry.service;

import com.wingflare.engine.task.server.retry.dto.RetryPartitionTask;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryDeadLetter;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2023-07-25 12:35
 * @since 2.0.3
 */
@Mapper
public interface RetryDeadLetterConverter {

    RetryDeadLetterConverter INSTANCE = Mappers.getMapper(RetryDeadLetterConverter.class);

    @Named("ignoreId")
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createDt", ignore = true)
    })
    RetryDeadLetter toRetryDeadLetter(RetryPartitionTask retryTasks);

    @IterableMapping(qualifiedByName = "ignoreId")
    List<RetryDeadLetter> toRetryDeadLetter(List<RetryPartitionTask> retries);

}
