package com.wingflare.engine.task.server.retry.support.handler;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies.WaitStrategyContext;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies.WaitStrategyEnum;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * 回调数据处理器
 *
 * @author opensnail
 * @date 2023-06-04
 * @since 1.5.0
 */
@Component
public class CallbackRetryTaskHandler {
    private static final Logger log = LoggerFactory.getLogger(CallbackRetryTaskHandler.class);
    private final AccessTemplate accessTemplate;


    public CallbackRetryTaskHandler(AccessTemplate accessTemplate) {
        this.accessTemplate = accessTemplate;
    }

    /**
     * 创建回调数据
     *
     * @param parentRetry            {@link Retry} 重试任务数据
     * @param retrySceneConfig
     */
    public void create(Retry parentRetry, RetrySceneConfig retrySceneConfig) {
        if (!SyetemTaskTypeEnum.RETRY.getType().equals(parentRetry.getTaskType())) {
            return;
        }

        if (Objects.isNull(retrySceneConfig.getCbStatus()) ||
                StatusEnum.NO.getStatus().equals(retrySceneConfig.getCbStatus())) {
            return;
        }

        Retry callbackRetry = RetryTaskConverter.INSTANCE.toRetryTask(parentRetry);
        callbackRetry.setTaskType(SyetemTaskTypeEnum.CALLBACK.getType());
        callbackRetry.setParentId(parentRetry.getId());
        callbackRetry.setRetryStatus(RetryStatusEnum.RUNNING.getStatus());
        callbackRetry.setRetryCount(0);
        callbackRetry.setCreateDt(LocalDateTime.now());
        callbackRetry.setUpdateDt(LocalDateTime.now());

        String triggerInterval = retrySceneConfig.getCbTriggerInterval();
        WaitStrategy waitStrategy = WaitStrategyEnum.getWaitStrategy(retrySceneConfig.getCbTriggerType());
        WaitStrategyContext waitStrategyContext = new WaitStrategyContext();
        waitStrategyContext.setNextTriggerAt(DateUtils.toNowMilli());
        waitStrategyContext.setDelayLevel(1);
        waitStrategyContext.setTriggerInterval(String.valueOf(triggerInterval));

        callbackRetry.setNextTriggerAt(waitStrategy.computeTriggerTime(waitStrategyContext));

        try {
            Assert.isTrue(1 == accessTemplate.getRetryAccess().insert(callbackRetry),
                    () -> new TaskServerException("failed to report data"));
        } catch (DuplicateKeyException e) {
            log.warn("Callback data duplicate insertion. [{}]", JsonUtil.toJsonString(callbackRetry));
        }
    }

//    /**
//     * 生成回调数据
//     *
//     * @param uniqueId 重试任务uniqueId
//     * @return 回调任务uniqueId
//     */
//    public String generatorCallbackUniqueId(String uniqueId) {
//        // eg: CB_202307180949471
//        FormattingTuple callbackUniqueId = MessageFormatter.arrayFormat(CALLBACK_UNIQUE_ID_RULE,
//                new Object[]{systemProperties.getCallback().getPrefix(), uniqueId});
//
//        return callbackUniqueId.getMessage();
//    }

    /**
     * 获取重试任务uniqueId
     *
     * @param callbackTaskUniqueId 回调任务uniqueId
     * @return 重试任务uniqueId
     */
    public String getRetryTaskUniqueId(String callbackTaskUniqueId) {
        return callbackTaskUniqueId.substring(callbackTaskUniqueId.lastIndexOf(StrUtil.UNDERLINE) + 1);
    }

}
