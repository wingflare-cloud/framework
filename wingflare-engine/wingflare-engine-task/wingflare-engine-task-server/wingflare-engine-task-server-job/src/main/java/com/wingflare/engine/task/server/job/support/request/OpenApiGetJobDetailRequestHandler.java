package com.wingflare.engine.task.server.job.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.convert.JobResponseVOConverter;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.vo.JobResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
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
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("Update job content:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        Long jobId = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), Long.class);
        Assert.notNull(jobId, () -> new TaskServerException("id cannot be null"));

        Job job = jobMapper.selectById(jobId);
        JobResponseVO convert = JobResponseVOConverter.INSTANCE.convert(job);
        return new TaskRpcResult(convert, retryRequest.getReqId());

    }
}
