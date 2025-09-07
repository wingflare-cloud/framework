package com.wingflare.engine.task.server.retry.support.schedule;


import cn.hutool.core.collection.CollUtil;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.dto.NotifyConfigDTO;
import com.wingflare.engine.task.server.retry.dto.RetrySceneConfigPartitionTask;
import com.wingflare.lib.alarm.AlarmUtil;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.lib.config.ConfigUtil;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 监控重试失败数据总量是否到达阈值
 *
 * @author: opensnail
 * @date : 2023-07-21 17:25
 * @since 2.1.0
 */
@Component
public class RetryErrorMoreThresholdAlarmSchedule extends AbstractRetryTaskAlarmSchedule implements Lifecycle {

    private static final String retryErrorMoreThresholdTextMessageFormatter =
            "<font face=\"微软雅黑\" color=#ff0000 size=4>{}环境 场景重试失败数量超过{}个</font>  \n" +
                    "> 空间ID:{}  \n" +
                    "> 组名称:{}  \n" +
                    "> 场景名称:{}  \n" +
                    "> 时间窗口:{} ~ {}  \n" +
                    "> **共计:{}**  \n";

    @Override
    public void start() {
        taskScheduler.scheduleWithFixedDelay(this::execute, Instant.now(), Duration.parse("PT10M"));
    }

    @Override
    public void close() {
    }

    @Override
    protected void doSendAlarm(RetrySceneConfigPartitionTask partitionTask, Map<Long, NotifyConfigDTO> notifyConfigInfo) {
        if (CollUtil.isEmpty(partitionTask.getNotifyIds())) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        // x分钟内、x组、x场景进入任务到达最大重试次数的数据量
        long count = accessTemplate.getRetryAccess()
                .count(new LambdaQueryWrapper<Retry>()
                        .eq(Retry::getNamespaceId, partitionTask.getNamespaceId())
                        .between(Retry::getUpdateDt, now.minusMinutes(30), now)
                        .eq(Retry::getGroupName, partitionTask.getGroupName())
                        .eq(Retry::getSceneName, partitionTask.getSceneName())
                        .eq(Retry::getRetryStatus, RetryStatusEnum.MAX_COUNT.getStatus())
                );

        for (Long notifyId : partitionTask.getNotifyIds()) {
            NotifyConfigDTO notifyConfigDTO = notifyConfigInfo.get(notifyId);
            if (notifyConfigDTO == null) {
                continue;
            }
            if (notifyConfigDTO.getNotifyThreshold() > 0 && count >= notifyConfigDTO.getNotifyThreshold()) {
                List<NotifyConfigDTO.RecipientInfo> recipientInfos = notifyConfigDTO.getRecipientInfos();
                for (final NotifyConfigDTO.RecipientInfo recipientInfo : recipientInfos) {
                    if (Objects.isNull(recipientInfo)) {
                        continue;
                    }
                    // 预警
                    AlarmContext context = AlarmContext.build()
                            .text(retryErrorMoreThresholdTextMessageFormatter,
                                    ConfigUtil.getProfiles(),
                                    count,
                                    partitionTask.getNamespaceId(),
                                    partitionTask.getGroupName(),
                                    partitionTask.getSceneName(),
                                    DateUtils.format(now.minusMinutes(30),
                                            DateUtils.NORM_DATETIME_PATTERN),
                                    DateUtils.toNowFormat(DateUtils.NORM_DATETIME_PATTERN), count)
                            .title("In {} environment, the number of scene retry failures exceeded the threshold",
                                    ConfigUtil.getProfiles())
                            .notifyAttribute(recipientInfo.getNotifyAttribute());
                    Optional.ofNullable(AlarmUtil.getAlarmType(recipientInfo.getNotifyType()))
                            .ifPresent(alarm -> AlarmUtil.asyncSendMessage(recipientInfo.getNotifyType(), context));
                }

            }
        }
    }

    @Override
    protected RetryNotifySceneEnum getNotifyScene() {
        return RetryNotifySceneEnum.MAX_RETRY_ERROR;
    }


    @Override
    public String lockName() {
        return "retryErrorMoreThreshold";
    }

    @Override
    public String lockExpire() {
        return "PT10M";
    }

    @Override
    public String lockTimeout() {
        return "PT1M";
    }
}
