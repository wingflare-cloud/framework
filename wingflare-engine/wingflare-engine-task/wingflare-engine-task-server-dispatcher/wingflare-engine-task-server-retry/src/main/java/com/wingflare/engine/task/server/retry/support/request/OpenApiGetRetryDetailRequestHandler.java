package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.model.SnailJobRequest;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.convert.RetryResponseVOConverter;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
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
    public SnailJobRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("query retry content:[{}]", content);
        SnailJobRequest retryRequest = JsonUtil.parseObject(content, SnailJobRequest.class);
        Object[] args = retryRequest.getArgs();
        Long retryId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);

        Retry retry = retryMapper.selectById(retryId);

        Assert.notNull(retry, () -> new SnailJobServerException("Retry task not found:[{}].", retryId));

        RetryResponseVO retryResponseVO = RetryResponseVOConverter.INSTANCE.convert(retry);
        return new SnailJobRpcResult(retryResponseVO, retryRequest.getReqId());
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
