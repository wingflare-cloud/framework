package com.wingflare.engine.task.server.common.convert;

import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.vo.RetryResponseVO;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper
@Deprecated
public interface RetryResponseVOConverter {
    RetryResponseVOConverter INSTANCE = Mappers.getMapper(RetryResponseVOConverter.class);

    @Mappings({
            @Mapping(target = "nextTriggerAt", expression = "java(RetryResponseVOConverter.toLocalDateTime(retry.getNextTriggerAt()))"),
    })
    RetryResponseVO convert(Retry retry);

    static LocalDateTime toLocalDateTime(Long nextTriggerAt) {
        if (Objects.isNull(nextTriggerAt) || nextTriggerAt == 0) {
            return null;
        }
        return DateUtils.toLocalDateTime(nextTriggerAt);
    }
}
