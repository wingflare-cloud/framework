package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.web.model.request.JobBatchResponseWebVO;
import com.wingflare.task.datasource.template.persistence.dataobject.JobBatchResponseDO;
import com.wingflare.task.datasource.template.persistence.po.Job;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author: shuguang.zhang
 * @date : 2023-10-12 10:23
 * @since : 2.4.0
 */
@Mapper
public interface JobBatchResponseVOConverter {

    JobBatchResponseVOConverter INSTANCE = Mappers.getMapper(JobBatchResponseVOConverter.class);

    List<JobBatchResponseWebVO> convertList(List<JobBatchResponseDO> jobBatches);

    @Mappings({
            @Mapping(target = "executionAt", expression = "java(JobBatchResponseVOConverter.toLocalDateTime(jobBatchResponseDO.getExecutionAt()))")
    })
    JobBatchResponseWebVO convert(JobBatchResponseDO jobBatchResponseDO);

    List<JobBatchResponseWebVO> convertListToJobBatchList(List<JobTaskBatch> jobTaskBatchList);

    @Mappings({
            @Mapping(target = "executionAt", expression = "java(JobBatchResponseVOConverter.toLocalDateTime(jobTaskBatch.getExecutionAt()))")
    })
    JobBatchResponseWebVO convert(JobTaskBatch jobTaskBatch);

    @Mappings({
            @Mapping(source = "jobBatch.groupName", target = "groupName"),
            @Mapping(source = "jobBatch.id", target = "id"),
            @Mapping(source = "jobBatch.createDt", target = "createDt"),
            @Mapping(source = "jobBatch.updateDt", target = "updateDt"),
            @Mapping(target = "executionAt", expression = "java(JobBatchResponseVOConverter.toLocalDateTime(jobBatch.getExecutionAt()))")
    })
    JobBatchResponseWebVO convert(JobTaskBatch jobBatch, Job job);

    static LocalDateTime toLocalDateTime(Long nextTriggerAt) {
        if (Objects.isNull(nextTriggerAt) || nextTriggerAt == 0) {
            return null;
        }

        return DateUtils.toLocalDateTime(nextTriggerAt);
    }

}
