package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.vo.RequestUpdateRetryStatusVO;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Deprecated
public class OpenApiUpdateRetryStatusRequestHandler extends PostHttpRequestHandler {

    private final RetryMapper retryMapper;

    private final AccessTemplate accessTemplate;

    public OpenApiUpdateRetryStatusRequestHandler(RetryMapper retryMapper, AccessTemplate accessTemplate) {
        this.retryMapper = retryMapper;
        this.accessTemplate = accessTemplate;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("update retry status:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        RequestUpdateRetryStatusVO updateRetryStatusVO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), RequestUpdateRetryStatusVO.class);

        RetryStatusEnum retryStatusEnum = RetryStatusEnum.getByStatus(updateRetryStatusVO.getRetryStatus());
        Assert.notNull(retryStatusEnum, () -> new TaskServerException("Retry status error. [{}]", updateRetryStatusVO.getRetryStatus()));

        Retry retry = retryMapper.selectById(updateRetryStatusVO.getId());
        Assert.notNull(retry, () -> new TaskServerException("Retry task not found:[{}].", retry.getId()));

        retry.setRetryStatus(updateRetryStatusVO.getRetryStatus());

        // 若恢复重试则需要重新计算下次触发时间
        if (RetryStatusEnum.RUNNING == retryStatusEnum) {
            RetrySceneConfig retrySceneConfig = accessTemplate.getSceneConfigAccess()
                    .getSceneConfigByGroupNameAndSceneName(retry.getGroupName(), retry.getSceneName(), retry.getNamespaceId());
            WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();
            waitStrategyContext.setNextTriggerAt(DateUtils.toNowMilli());
            waitStrategyContext.setTriggerInterval(retrySceneConfig.getTriggerInterval());
            waitStrategyContext.setDelayLevel(retry.getRetryCount() + 1);
            WaitStrategy waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(retrySceneConfig.getBackOff());
            retry.setNextTriggerAt(waitStrategy.computeTriggerTime(waitStrategyContext));
        }

        retry.setUpdateDt(LocalDateTime.now());

        Assert.isTrue(retryMapper.updateById(retry) == 1, () -> new TaskServerException("Update status of retry task failed:[{}].", retry.getId()));

        return new TaskRpcResult(true, retryRequest.getReqId());
    }

    @Override
    public boolean supports(String path) {
        return SystemConstants.HTTP_PATH.OPENAPI_UPDATE_RETRY_STATUS.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }
}
