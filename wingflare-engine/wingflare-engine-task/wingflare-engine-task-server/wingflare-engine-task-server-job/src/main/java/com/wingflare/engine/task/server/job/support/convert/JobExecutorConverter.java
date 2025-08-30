package com.wingflare.engine.task.server.job.support.convert;

import com.wingflare.engine.task.common.model.request.JobExecutorRequest;
import com.wingflare.task.datasource.template.persistence.po.JobExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author：srzou
 * @Project：snail-job
 * @since 1.6.0
 */
@Mapper
public interface JobExecutorConverter {

    JobExecutorConverter INSTANCE = Mappers.getMapper(JobExecutorConverter.class);


    List<JobExecutor> toJobExecutor(List<JobExecutorRequest> jobExecutorRequests);

}
