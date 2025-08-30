package com.wingflare.engine.task.server.retry.support.schedule;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.wingflare.engine.task.server.common.dto.PartitionTask;
import com.wingflare.engine.task.server.common.schedule.AbstractSchedule;
import com.wingflare.engine.task.server.common.util.PartitionTaskUtils;
import com.wingflare.engine.task.server.retry.dto.NotifyConfigDTO;
import com.wingflare.engine.task.server.retry.dto.RetrySceneConfigPartitionTask;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.task.datasource.template.access.AccessTemplate;
import com.wingflare.task.datasource.template.persistence.mapper.NotifyRecipientMapper;
import com.wingflare.task.datasource.template.persistence.po.NotifyConfig;
import com.wingflare.task.datasource.template.persistence.po.NotifyRecipient;
import com.wingflare.task.datasource.template.persistence.po.RetrySceneConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author opensnail
 * @date 2025-01-11
 * @since 1.3.0-beta1.1
 */
public abstract class AbstractRetryTaskAlarmSchedule extends AbstractSchedule implements Lifecycle {
    @Autowired
    protected AccessTemplate accessTemplate;
    @Autowired
    private NotifyRecipientMapper recipientMapper;

    @Override
    protected void doExecute() {
        PartitionTaskUtils.process(this::queryPartitionList, this::doHandler, 0);
    }

    /**
     * 循环场景信息
     *
     * @param partitionTasks 需要告警的场景信息
     */
    private void doHandler(List<? extends PartitionTask> partitionTasks) {

        // 处理通知信息
        Map<Long, NotifyConfigDTO> notifyConfigInfo = getNotifyConfigInfo((List<RetrySceneConfigPartitionTask>) partitionTasks);
        if (notifyConfigInfo.isEmpty()) {
            return;
        }

        for (PartitionTask partitionTask : partitionTasks) {
            doSendAlarm((RetrySceneConfigPartitionTask) partitionTask, notifyConfigInfo);
        }
    }

    protected abstract void doSendAlarm(RetrySceneConfigPartitionTask partitionTask, Map<Long, NotifyConfigDTO> notifyConfigInfo);

    protected abstract RetryNotifySceneEnum getNotifyScene();

    /**
     * 获取需要处理的配置信息
     *
     * @param startId 偏移id
     * @return 需要处理的场景列表
     */
    protected List<RetrySceneConfigPartitionTask> queryPartitionList(Long startId) {
        List<RetrySceneConfig> retrySceneConfigList = accessTemplate.getSceneConfigAccess()
                .listPage(new PageDTO<>(0, 500, Boolean.FALSE),
                        new LambdaQueryWrapper<RetrySceneConfig>()
                                .gt(RetrySceneConfig::getId, startId)
                                .eq(RetrySceneConfig::getSceneStatus, StatusEnum.YES.getStatus())
                                .orderByAsc(RetrySceneConfig::getId)
                ).getRecords();
        return RetryTaskConverter.INSTANCE.toRetrySceneConfigPartitionTask(retrySceneConfigList);
    }


    /**
     * 获取通知信息
     * @param partitionTasks 本次需要处理的场景列表
     * @return Map<Long(通知配置id), NotifyConfigDTO(配置信息)>
     */
    protected Map<Long, NotifyConfigDTO> getNotifyConfigInfo(List<RetrySceneConfigPartitionTask> partitionTasks) {

        // 提前通知配置id
        Set<Long> retryNotifyIds = partitionTasks.stream()
                .map(RetrySceneConfigPartitionTask::getNotifyIds)
                .filter(CollUtil::isNotEmpty)
                .reduce((a, b) -> {
                    HashSet<Long> set = Sets.newHashSet();
                    set.addAll(a);
                    set.addAll(b);
                    return set;
                }).orElse(new HashSet<>());

        if (CollUtil.isEmpty(retryNotifyIds)) {
            return Maps.newHashMap();
        }

        // 从DB中获取通知配置信息
        List<NotifyConfigDTO> notifyConfigs = RetryTaskConverter.INSTANCE.toNotifyConfigDTO(accessTemplate.getNotifyConfigAccess()
                .list(new LambdaQueryWrapper<NotifyConfig>()
                        .in(NotifyConfig::getId, retryNotifyIds)
                        .eq(NotifyConfig::getNotifyStatus, StatusEnum.YES.getStatus())
                        .eq(NotifyConfig::getNotifyScene, getNotifyScene().getNotifyScene())
                        .orderByAsc(NotifyConfig::getId)));
        if (CollUtil.isEmpty(notifyConfigs)) {
            return Maps.newHashMap();
        }

        // 提前通知人信息
        Set<Long> recipientIds = notifyConfigs.stream()
                .map(NotifyConfigDTO::getRecipientIds)
                .filter(CollUtil::isNotEmpty)
                .reduce((a, b) -> {
                    HashSet<Long> set = Sets.newHashSet();
                    set.addAll(a);
                    set.addAll(b);
                    return set;
                }).orElse(new HashSet<>());

        if (CollUtil.isEmpty(recipientIds)) {
            return Maps.newHashMap();
        }

        // 从DB中获取通知人信息
        List<NotifyRecipient> notifyRecipients = recipientMapper.selectByIds(recipientIds);
        Map<Long, NotifyRecipient> recipientMap = StreamUtils.toIdentityMap(notifyRecipients, NotifyRecipient::getId);

        Map<Long, NotifyConfigDTO> notifyConfigMap = Maps.newHashMap();
        for (final NotifyConfigDTO notifyConfigDTO : notifyConfigs) {

            List<NotifyConfigDTO.RecipientInfo> recipientList = StreamUtils.toList(notifyConfigDTO.getRecipientIds(),
                    recipientId -> {
                        NotifyRecipient notifyRecipient = recipientMap.get(recipientId);
                        if (Objects.isNull(notifyRecipient)) {
                            return null;
                        }

                        NotifyConfigDTO.RecipientInfo recipientInfo = new NotifyConfigDTO.RecipientInfo();
                        recipientInfo.setNotifyType(notifyRecipient.getNotifyType());
                        recipientInfo.setNotifyAttribute(notifyRecipient.getNotifyAttribute());

                        return recipientInfo;
                    });

            notifyConfigDTO.setRecipientInfos(recipientList);
            notifyConfigMap.put(notifyConfigDTO.getId(), notifyConfigDTO);
        }

        return notifyConfigMap;
    }

}
