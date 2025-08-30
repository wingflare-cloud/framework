package com.wingflare.engine.task.server.retry.support.result;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.RetryOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.retry.dto.RetryTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.event.RetryTaskFailAlarmEvent;
import com.wingflare.engine.task.server.retry.support.handler.CallbackRetryTaskHandler;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.Retry;
import com.wingflare.task.datasource.template.persistence.po.RetrySceneConfig;
import com.wingflare.task.datasource.template.persistence.po.RetryTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum.RETRY_TASK_FAIL_ERROR;
import static com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum.NOT_COMPLETE;
import static com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum.NOT_SUCCESS;

/**
 * <p>
 * 客户端执行重试失败、服务端调度时失败等场景导致的任务执行失败
 * </p>
 *
 * @author opensnail
 * @date 2025-02-02
 */
@Component
public class RetryFailureHandler extends AbstractRetryResultHandler {
    private final AccessTemplate accessTemplate;
    private final CallbackRetryTaskHandler callbackRetryTaskHandler;
    private final TransactionTemplate transactionTemplate;
    private final RetryTaskMapper retryTaskMapper;
    private final RetryMapper retryMapper;

    public RetryFailureHandler(AccessTemplate accessTemplate, CallbackRetryTaskHandler callbackRetryTaskHandler, TransactionTemplate transactionTemplate, RetryTaskMapper retryTaskMapper, RetryMapper retryMapper) {
        this.accessTemplate = accessTemplate;
        this.callbackRetryTaskHandler = callbackRetryTaskHandler;
        this.transactionTemplate = transactionTemplate;
        this.retryTaskMapper = retryTaskMapper;
        this.retryMapper = retryMapper;
    }

    @Override
    public boolean supports(RetryResultContext context) {
        RetryOperationReasonEnum reasonEnum = RetryOperationReasonEnum.of(context.getOperationReason());
        return NOT_SUCCESS.contains(context.getTaskStatus())
                && RetryOperationReasonEnum.CLIENT_TRIGGER_RETRY_STOP.getReason() != reasonEnum.getReason();
    }

    @Override
    public void doHandler(RetryResultContext context) {
        RetrySceneConfig retrySceneConfig =
                accessTemplate.getSceneConfigAccess().getSceneConfigByGroupNameAndSceneName(
                        context.getGroupName(), context.getSceneName(), context.getNamespaceId());

        Retry retry = retryMapper.selectById(context.getRetryId());
        transactionTemplate.execute(status -> {

            RetryTask retryTask = new RetryTask();
            retryTask.setTaskStatus(Optional.ofNullable(context.getTaskStatus()).orElse(RetryTaskStatusEnum.FAIL.getStatus()));
            retryTask.setOperationReason(Optional.ofNullable(context.getOperationReason()).orElse(RetryOperationReasonEnum.NONE.getReason()));
            int update = retryTaskMapper.update(retryTask, new LambdaQueryWrapper<RetryTask>()
                    .eq(RetryTask::getId, context.getRetryTaskId())
                    .in(RetryTask::getTaskStatus, NOT_COMPLETE));
            if (update <= 0) {
                return null;
            }

            Integer maxRetryCount;
            if (SyetemTaskTypeEnum.CALLBACK.getType().equals(retry.getTaskType())) {
                maxRetryCount = retrySceneConfig.getCbMaxCount();
            } else {
                maxRetryCount = retrySceneConfig.getMaxRetryCount();
            }

            int current = retry.getRetryCount() + 1;
            if (maxRetryCount <= current) {
                retry.setRetryStatus(RetryStatusEnum.MAX_COUNT.getStatus());
                retry.setRetryCount(current);
                retry.setUpdateDt(LocalDateTime.now());
                retry.setDeleted(retry.getId());
                Assert.isTrue(1 == retryMapper.updateById(retry),
                        () -> new SnailJobServerException("Update retry task failed. Group name:[{}]", retry.getGroupName()));
                // 创建一个回调任务
                callbackRetryTaskHandler.create(retry, retrySceneConfig);
            } else if (context.isIncrementRetryCount()) {
                retry.setRetryCount(current);
                retry.setUpdateDt(LocalDateTime.now());
                Assert.isTrue(1 == retryMapper.updateById(retry),
                        () -> new SnailJobServerException("Update retry task failed. Group name:[{}]", retry.getGroupName()));

            }

            RetryTaskFailAlarmEventDTO retryTaskFailAlarmEventDTO =
                    RetryTaskConverter.INSTANCE.toRetryTaskFailAlarmEventDTO(
                            retry, context.getExceptionMsg(), RETRY_TASK_FAIL_ERROR.getNotifyScene());
            SnailSpringContext.getContext().publishEvent(new RetryTaskFailAlarmEvent(retryTaskFailAlarmEventDTO));

            return null;
        });
    }
}
