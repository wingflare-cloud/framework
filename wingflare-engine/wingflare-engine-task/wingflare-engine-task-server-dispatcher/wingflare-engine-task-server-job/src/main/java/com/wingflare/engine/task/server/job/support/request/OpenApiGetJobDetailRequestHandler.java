package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.SnailJobRequest;
import com.wingflare.engine.task.common.core.model.SnailJobRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.convert.JobResponseVOConverter;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.JobResponseVO;
import com.wingflare.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.task.datasource.template.persistence.po.Job;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * OPENAPI
 * 获取定时任务详情
 */
@Component
@Deprecated
public class OpenApiGetJobDetailRequestHandler extends PostHttpRequestHandler {
    private final JobMapper jobMapper;

    public OpenApiGetJobDetailRequestHandler(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.OPENAPI_GET_JOB_DETAIL.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public SnailJobRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("Update job content:[{}]", content);
        SnailJobRequest retryRequest = JsonUtil.parseObject(content, SnailJobRequest.class);
        Object[] args = retryRequest.getArgs();
        Long jobId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);
        Assert.notNull(jobId, () -> new SnailJobServerException("id cannot be null"));

        Job job = jobMapper.selectById(jobId);
        JobResponseVO convert = JobResponseVOConverter.INSTANCE.convert(job);
        return new SnailJobRpcResult(convert, retryRequest.getReqId());

    }
}
