package com.wingflare.engine.task.server.common.alarm;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.convert.AlarmInfoConverter;
import com.wingflare.engine.task.server.common.dto.WorkflowAlarmInfo;
import com.wingflare.task.datasource.template.persistence.dataobject.WorkflowBatchResponseDO;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaowoniu
 * @date 2023-12-03 10:19:19
 * @since 2.5.0
 */
public abstract class AbstractWorkflowAlarm<E extends ApplicationEvent> extends AbstractAlarm<E, WorkflowAlarmInfo> {

    @Resource
    private WorkflowTaskBatchMapper workflowTaskBatchMapper;

    @Override
    protected Map<Long, List<WorkflowAlarmInfo>> convertAlarmDTO(List<WorkflowAlarmInfo> workflowAlarmInfoList, Set<Integer> notifyScene) {

        Map<Long, List<WorkflowAlarmInfo>> workflowAlarmInfoMap = new HashMap<>();
        workflowAlarmInfoList.forEach(i -> notifyScene.add(i.getNotifyScene()));

        Map<Long, WorkflowAlarmInfo> workflowAlarmInfoGroupMap = StreamUtils.toIdentityMap(workflowAlarmInfoList, WorkflowAlarmInfo::getId);
        // 查询数据库
        List<WorkflowBatchResponseDO> workflowBatchResponseDOList = workflowTaskBatchMapper.selectWorkflowBatchList(
                new QueryWrapper<WorkflowTaskBatch>()
                        .in("batch.id", workflowAlarmInfoList.stream().map(i -> i.getId()).collect(Collectors.toSet()))
                        .eq("batch.deleted", 0));

        for (WorkflowBatchResponseDO workflowBatchResponseDO : workflowBatchResponseDOList) {
            Set<Long> workflowNotifyIds = StrUtil.isBlank(workflowBatchResponseDO.getNotifyIds()) ? new HashSet<>() : new HashSet<>(JsonUtil.parseList(workflowBatchResponseDO.getNotifyIds(), Long.class));
            for (Long workflowNotifyId : workflowNotifyIds) {

                WorkflowAlarmInfo workflowAlarmInfo = AlarmInfoConverter.INSTANCE.toWorkflowAlarmInfo(workflowBatchResponseDO);
                WorkflowAlarmInfo alarmInfo = workflowAlarmInfoGroupMap.get(workflowAlarmInfo.getId());
                workflowAlarmInfo.setReason(alarmInfo.getReason());
                workflowAlarmInfo.setNotifyScene(alarmInfo.getNotifyScene());

                List<WorkflowAlarmInfo> workflowAlarmInfos = workflowAlarmInfoMap.getOrDefault(workflowNotifyId, Lists.newArrayList());
                workflowAlarmInfos.add(workflowAlarmInfo);
                workflowAlarmInfoMap.put(workflowNotifyId, workflowAlarmInfos);
            }
        }

        return workflowAlarmInfoMap;
    }
}
