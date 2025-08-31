package com.wingflare.engine.task.server.retry.support.request;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.enums.RetryTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.PostHttpRequestHandler;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.vo.RequestTriggerRetryVO;
import com.wingflare.engine.task.server.retry.convert.RetryConverter;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Deprecated
public class OpenApiTriggerRetryRequestHandler extends PostHttpRequestHandler {

    private final RetryMapper retryMapper;

    private final AccessTemplate accessTemplate;

    public OpenApiTriggerRetryRequestHandler(RetryMapper retryMapper, AccessTemplate accessTemplate) {
        this.retryMapper = retryMapper;
        this.accessTemplate = accessTemplate;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        TaskEngineLog.LOCAL.debug("trigger retry:[{}]", content);
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        Object[] args = retryRequest.getArgs();
        RequestTriggerRetryVO triggerRetryVO = JsonUtil.parseObject(JsonUtil.toJsonString(args[0]), RequestTriggerRetryVO.class);

        Retry retry = retryMapper.selectById(triggerRetryVO.getId());

        Assert.notNull(retry, () -> new TaskServerException("Retry task not found:[{}].", triggerRetryVO.getId()));

        long count = accessTemplate.getGroupConfigAccess().count(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getGroupName, retry.getGroupName())
                .eq(GroupConfig::getNamespaceId, retry.getNamespaceId())
                .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
        );

        Assert.isTrue(count > 0, () -> new TaskServerException("Group [{}] is closed, manual execution is not supported.", retry.getGroupName()));

        Assert.isTrue(Objects.equals(retry.getTaskType(), SyetemTaskTypeEnum.RETRY.getType()), () -> new TaskServerException("No executable tasks"));

        RetryTaskPrepareDTO retryTaskPrepareDTO = RetryConverter.INSTANCE.toRetryTaskPrepareDTO(retry);
        // 设置now表示立即执行
        retryTaskPrepareDTO.setNextTriggerAt(DateUtils.toNowMilli());
        retryTaskPrepareDTO.setRetryTaskExecutorScene(RetryTaskExecutorSceneEnum.MANUAL_RETRY.getScene());
        retryTaskPrepareDTO.setRetryId(retry.getId());
        // 准备阶段执行
        ActorRef actorRef = ActorGenerator.retryTaskPrepareActor();
        actorRef.tell(retryTaskPrepareDTO, actorRef);
        return new TaskRpcResult(true, retryRequest.getReqId());
    }

    @Override
    public boolean supports(String path) {
        return SystemConstants.HTTP_PATH.OPENAPI_TRIGGER_RETRY.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }
}
