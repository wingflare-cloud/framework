package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.convert.RetryResponseVOConverter;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.RetryResponseVO;
import com.wingflare.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class OpenApiGetRetryDetailRequestHandler extends PostHttpRequestHandler {

    private final RetryMapper retryMapper;

    public OpenApiGetRetryDetailRequestHandler(RetryMapper retryMapper) {
        this.retryMapper = retryMapper;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("query retry content:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        Long retryId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);

        Retry retry = retryMapper.selectById(retryId);

        Assert.notNull(retry, () -> new TaskServerException("Retry task not found:[{}].", retryId));

        RetryResponseVO retryResponseVO = RetryResponseVOConverter.INSTANCE.convert(retry);
        return new TaskRpcResult(retryResponseVO, retryRequest.getReqId());
    }

    @Override
    public boolean supports(String path) {
        return SystemConstants.HTTP_PATH.OPENAPI_QUERY_RETRY.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }
}
