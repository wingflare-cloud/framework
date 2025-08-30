package com.wingflare.engine.task.server.job.client;

import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.DispatchJobRequest;
import com.wingflare.engine.task.common.model.request.StopJobRequest;
import com.wingflare.engine.task.server.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.server.common.rpc.client.annotation.Body;
import com.wingflare.engine.task.server.common.rpc.client.annotation.Mapping;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.JOB_DISPATCH;
import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.JOB_STOP;

/**
 * 调用客户端接口
 *
 * @author: opensnail
 * @date : 2023-06-19 15:40
 * @since 2.0.0
 */
public interface JobRpcClient {

    @Mapping(path = JOB_STOP, method = RequestMethod.POST)
    Result<Boolean> stop(@Body StopJobRequest stopJobRequest);

    @Mapping(path = JOB_DISPATCH, method = RequestMethod.POST)
    Result<Boolean> dispatch(@Body DispatchJobRequest dispatchJobRequest);

}
