package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.DispatchJobResultRequest;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.job.support.ClientCallbackHandler;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.callback.ClientCallbackContext;
import com.wingflare.engine.task.server.job.support.callback.ClientCallbackFactory;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.REPORT_JOB_DISPATCH_RESULT;

/**
 * @author opensnail
 * @date 2023-09-30 23:01:58
 * @since 2.4.0
 */
@Component
public class ReportDispatchResultPostHttpRequestHandler extends PostHttpRequestHandler {

    @Override
    public boolean supports(String path) {
        return REPORT_JOB_DISPATCH_RESULT.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("Client Callback Request. content:[{}]", content);

        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();

        DispatchJobResultRequest dispatchJobResultRequest = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), DispatchJobResultRequest.class);

        ClientCallbackHandler clientCallback = ClientCallbackFactory.getClientCallback(dispatchJobResultRequest.getTaskType());

        ClientCallbackContext context = JobTaskConverter.INSTANCE.toClientCallbackContext(dispatchJobResultRequest);
        context.setNamespaceId(headers.getAsString(HeadersEnum.NAMESPACE.getKey()));
        clientCallback.callback(context);

        return new TaskRpcResult(StatusEnum.YES.getStatus(), "Report Dispatch Result Processed Successfully", Boolean.TRUE, retryRequest.getReqId());
    }
}
