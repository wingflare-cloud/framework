package com.wingflare.engine.task.client.core.client;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.DispatchJobResultRequest;
import com.wingflare.engine.task.common.model.request.MapTaskRequest;

/**
 * netty 客户端请求类
 *
 * @author: opensnail
 * @date : 2023-05-11 21:28
 * @since 2.4.0
 */
public interface JobNettyClient {

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.REPORT_JOB_DISPATCH_RESULT)
    Result dispatchResult(DispatchJobResultRequest request);

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.BATCH_REPORT_JOB_MAP_TASK)
    Result<Boolean> batchReportMapTask(MapTaskRequest mapTaskRequest);
}
