package com.wingflare.engine.task.server.service.service.impl;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.model.request.RetryArgsDeserializeRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.request.base.TriggerRetryRequest;
import com.wingflare.engine.task.common.model.response.base.RetryResponse;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.enums.RetryTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.service.convert.RetryConverter;
import com.wingflare.engine.task.server.service.handler.RetryArgsDeserializeHandler;
import com.wingflare.engine.task.server.service.service.RetryService;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.access.TaskAccess;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import com.wingflare.task.datasource.template.persistence.po.RetrySceneConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.pekko.actor.ActorRef;
import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-25
 */
public abstract class AbstractRetryService implements RetryService {
    @Resource
    private AccessTemplate accessTemplate;
    @Resource
    private RetryArgsDeserializeHandler retryArgsDeserializeHandler;

    @Override
    public <T extends RetryResponse> T getRetryById(Long retryId, Class<T> clazz) {
        Retry retry = accessTemplate.getRetryAccess().one(new LambdaQueryWrapper<Retry>().eq(Retry::getId, retryId));

        Assert.notNull(retry, () -> new TaskServerException("Retry task not found:[{}].", retryId));

        T instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
            RetryConverter.INSTANCE.toRetryResponseVO(retry, instance);

            RetryArgsDeserializeRequest deserializeDTO = new RetryArgsDeserializeRequest();
            deserializeDTO.setArgsStr(retry.getArgsStr());
            deserializeDTO.setExecutorName(retry.getExecutorName());
            deserializeDTO.setScene(retry.getSceneName());
            deserializeDTO.setGroup(retry.getGroupName());
            deserializeDTO.setSerializerName(retry.getSerializerName());
            deserializeDTO.setNamespaceId(retry.getNamespaceId());
            instance.setArgsStr(retryArgsDeserializeHandler.deserialize(deserializeDTO));
        } catch (Exception e) {
            throw new TaskServerException("Failed to get retry by id [{}]", retryId, e);
        }

        return instance;
    }

    @Override
    public boolean triggerRetry(TriggerRetryRequest triggerRetryRequest) {
        Retry retry = accessTemplate.getRetryAccess().one(new LambdaQueryWrapper<Retry>().eq(Retry::getId, triggerRetryRequest.getId()));
        Assert.notNull(retry, () -> new TaskServerException("Retry task not found:[{}].", triggerRetryRequest.getId()));
        long count = accessTemplate.getGroupConfigAccess().count(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getGroupName, retry.getGroupName())
                .eq(GroupConfig::getNamespaceId, getNamespaceId())
                .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
        );

        Assert.isTrue(count > 0, () -> new TaskServerException("Group [{}] is closed, manual execution is not supported.", retry.getGroupName()));

        Assert.isTrue(Objects.equals(retry.getTaskType(), SyetemTaskTypeEnum.RETRY.getType()), () -> new TaskServerException("No executable tasks"));

        RetryTaskPrepareDTO retryTaskPrepareDTO = com.wingflare.engine.task.server.retry.convert.RetryConverter.INSTANCE.toRetryTaskPrepareDTO(retry);
        // 设置now表示立即执行
        retryTaskPrepareDTO.setNextTriggerAt(DateUtils.toNowMilli());
        retryTaskPrepareDTO.setRetryTaskExecutorScene(RetryTaskExecutorSceneEnum.MANUAL_RETRY.getScene());
        retryTaskPrepareDTO.setRetryId(retry.getId());
        // 准备阶段执行
        ActorRef actorRef = ActorGenerator.retryTaskPrepareActor();
        actorRef.tell(retryTaskPrepareDTO, actorRef);

        return true;
    }

    @Override
    public boolean updateRetryStatus(StatusUpdateRequest requestDTO) {
        RetryStatusEnum retryStatusEnum = RetryStatusEnum.getByStatus(requestDTO.getStatus());
        if (Objects.isNull(retryStatusEnum)) {
            throw new TaskServerException("Retry status error. [{}]", requestDTO.getStatus());
        }

        TaskAccess<Retry> retryTaskAccess = accessTemplate.getRetryAccess();
        Retry retry = retryTaskAccess.one(new LambdaQueryWrapper<Retry>()
                .eq(Retry::getNamespaceId, getNamespaceId())
                .eq(Retry::getId, requestDTO.getId()));
        if (Objects.isNull(retry)) {
            throw new TaskServerException("Retry task not found");
        }

        retry.setRetryStatus(requestDTO.getStatus());

        // 若恢复重试则需要重新计算下次触发时间
        if (RetryStatusEnum.RUNNING.getStatus().equals(retryStatusEnum.getStatus())) {

            RetrySceneConfig retrySceneConfig = accessTemplate.getSceneConfigAccess()
                    .getSceneConfigByGroupNameAndSceneName(retry.getGroupName(), retry.getSceneName(), getNamespaceId());
            WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();
            waitStrategyContext.setNextTriggerAt(DateUtils.toNowMilli());
            waitStrategyContext.setTriggerInterval(retrySceneConfig.getTriggerInterval());
            waitStrategyContext.setDelayLevel(retry.getRetryCount() + 1);
            WaitStrategy waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(retrySceneConfig.getBackOff());
            retry.setNextTriggerAt(waitStrategy.computeTriggerTime(waitStrategyContext));
            retry.setDeleted(0L);
        }

        if (RetryStatusEnum.FINISH.getStatus().equals(retryStatusEnum.getStatus())) {
            retry.setDeleted(retry.getId());
        }

        retry.setUpdateDt(LocalDateTime.now());

        return retryTaskAccess.updateById(retry) == 1;
    }

    protected abstract String getNamespaceId();
}
