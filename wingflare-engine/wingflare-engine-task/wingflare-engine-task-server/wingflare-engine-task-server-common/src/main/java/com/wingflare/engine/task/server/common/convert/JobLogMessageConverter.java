package com.wingflare.engine.task.server.common.convert;

import com.wingflare.engine.task.common.model.request.JobLogTaskRequest;
import com.wingflare.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.common.support
 * @Project：snail-job
 * @Date：2025/3/10 21:16
 * @Filename：JobLogMessageConverter
 * @since 1.5.0
 */
@Mapper
@Deprecated
public interface JobLogMessageConverter {

    JobLogMessageConverter INSTANCE = Mappers.getMapper(JobLogMessageConverter.class);


    List<JobLogMessageDO> toJobLogMessages(List<JobLogTaskRequest> jobLogDTOs);

}
