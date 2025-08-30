package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.DispatchCallbackResultRequest;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RetryExecutorResultDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.REPORT_CALLBACK_RESULT;

/**
 * 上报回调执行的处理结果
 *
 * @author: opensnail
 * @date : 2022-03-07 16:39
 * @since 1.0.0
 */
@Component
public class ReportCallbackResultHttpRequestHandler extends PostHttpRequestHandler {

    @Override
    public boolean supports(String path) {
        return REPORT_CALLBACK_RESULT.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    @Transactional
    public TaskRpcResult doHandler(String content, UrlQuery urlQuery, HttpHeaders headers) {
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();

        try {
            DispatchCallbackResultRequest request = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), DispatchCallbackResultRequest.class);
            RetryExecutorResultDTO executorResultDTO = RetryTaskConverter.INSTANCE.toRetryExecutorResultDTO(request);
            RetryTaskStatusEnum statusEnum = RetryTaskStatusEnum.getByStatus(request.getTaskStatus());
            Assert.notNull(statusEnum, () -> new TaskServerException("task status code is invalid"));
            executorResultDTO.setIncrementRetryCount(true);
            ActorRef actorRef = ActorGenerator.retryTaskExecutorResultActor();
            actorRef.tell(executorResultDTO, actorRef);

            return new TaskRpcResult(StatusEnum.YES.getStatus(), "Report dispatch result processed successfully", Boolean.TRUE, retryRequest.getReqId());
        } catch (Exception e) {
            return new TaskRpcResult(StatusEnum.YES.getStatus(), e.getMessage(), Boolean.FALSE, retryRequest.getReqId());
        }
    }

}
