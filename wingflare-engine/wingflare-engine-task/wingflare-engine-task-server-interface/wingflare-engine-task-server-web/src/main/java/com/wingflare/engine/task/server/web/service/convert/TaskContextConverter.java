package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import com.wingflare.engine.task.server.retry.support.generator.retry.TaskContext;
import com.wingflare.engine.task.server.web.model.request.RetrySaveRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-07-16 22:09:40
 * @since 2.1.0
 */
@Mapper
public interface TaskContextConverter {
    TaskContextConverter INSTANCE = Mappers.getMapper(TaskContextConverter.class);

    TaskContext.TaskInfo convert(RetrySaveRequestVO retrySaveRequestVO);

    List<TaskContext.TaskInfo> convert(List<RetryTaskRequest> retryTasks);
}
