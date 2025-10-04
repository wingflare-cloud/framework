package com.wingflare.engine.task.client.retry.client;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.DispatchCallbackResultRequest;
import com.wingflare.engine.task.common.model.request.DispatchRetryResultRequest;

/**
 * netty 客户端请求类
 *
 * @author: opensnail
 * @date : 2023-05-11 21:28
 * @since 2.4.0
 */
public interface RetryClient {

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.REPORT_RETRY_DISPATCH_RESULT)
    Result dispatchResult(DispatchRetryResultRequest request);

    @Mapping(method = RequestMethod.POST, path = HTTP_PATH.REPORT_CALLBACK_RESULT)
    Result callbackResult(DispatchCallbackResultRequest request);
}
