package com.wingflare.engine.task.client.common;

import com.wingflare.engine.task.client.common.annotation.Header;
import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.model.request.JobExecutorRequest;
import com.wingflare.engine.task.common.model.request.LogTaskRequest;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;

import java.util.List;
import java.util.Map;


/**
 * rpc 客户端请求类
 *
 * @author: opensnail
 * @date : 2023-05-11 21:28
 * @since 1.3.0
 */
public interface RpcClient {

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.BATCH_REPORT)
    TaskRpcResult reportRetryInfo(List<RetryTaskRequest> list);

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.BATCH_LOG_REPORT)
    TaskRpcResult reportLogTask(List<LogTaskRequest> list);

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.SYNC_CONFIG)
    Result syncRemoteConfig();

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.BEAT)
    Result beat(String mark, @Header(name = HeadersEnum.LABEL) Map<String, String> labels);

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.REGISTER_JOB_EXECUTORS)
    Result registryExecutors(List<JobExecutorRequest> contextList);

}
