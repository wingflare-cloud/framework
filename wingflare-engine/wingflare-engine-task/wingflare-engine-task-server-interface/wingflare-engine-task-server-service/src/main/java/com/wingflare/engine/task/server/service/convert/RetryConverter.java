package com.wingflare.engine.task.server.service.convert;

import com.wingflare.engine.task.common.model.response.base.RetryResponse;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface RetryConverter {
    RetryConverter INSTANCE = Mappers.getMapper(RetryConverter.class);

    @Mappings({
            @Mapping(target = "nextTriggerAt", expression = "java(RetryConverter.toLocalDateTime(retry.getNextTriggerAt()))"),
    })
    void toRetryResponseVO(Retry retry, @MappingTarget RetryResponse baseDTO);

    static LocalDateTime toLocalDateTime(Long nextTriggerAt) {
        return JobConverter.toLocalDateTime(nextTriggerAt);
    }
}
