package com.wingflare.engine.task.server.service.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.base.JobRequest;
import com.wingflare.engine.task.common.model.response.base.JobResponse;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.util.TriggerIntervalUtils;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
@Mapper
public interface JobConverter {
    JobConverter INSTANCE = Mappers.getMapper(JobConverter.class);

    @Mappings({
            @Mapping(target = "notifyIds", expression = "java(JobConverter.toNotifyIdsStr(jobRequest.getNotifyIds()))"),
            @Mapping(target = "triggerInterval", expression = "java(JobConverter.toTriggerInterval(jobRequest))")
    })
    Job convert(JobRequest jobRequest);


    @Mappings({
            @Mapping(target = "nextTriggerAt", expression = "java(com.wingflare.engine.task.server.common.util.DateUtils.toLocalDateTime(job.getNextTriggerAt()))"),
            @Mapping(target = "notifyIds", expression = "java(JobConverter.toNotifyIds(job.getNotifyIds()))")
    })
    void fillCommonFields(Job job, @MappingTarget JobResponse target);

    static LocalDateTime toLocalDateTime(Long nextTriggerAt) {
        if (Objects.isNull(nextTriggerAt) || nextTriggerAt == 0) {
            return null;
        }

        return DateUtils.toLocalDateTime(nextTriggerAt);
    }

    static Set<Long> toNotifyIds(String notifyIds) {
        if (StrUtil.isBlank(notifyIds)) {
            return new HashSet<>();
        }

        return new HashSet<>(JsonUtil.parseList(notifyIds, Long.class));
    }

    static String toNotifyIdsStr(Set<Long> notifyIds) {
        if (CollUtil.isEmpty(notifyIds)) {
            return StrUtil.EMPTY;
        }

        return JsonUtil.toJsonString(notifyIds);
    }

    static String toTriggerInterval(JobRequest jobRequestVO) {
        String triggerInterval = jobRequestVO.getTriggerInterval();
        if (StrUtil.isBlank(triggerInterval) || Objects.isNull(jobRequestVO.getTriggerType())) {
            return StrUtil.EMPTY;
        }

        return TriggerIntervalUtils.getPointInTimeStr(triggerInterval, jobRequestVO.getTriggerType());
    }
}

