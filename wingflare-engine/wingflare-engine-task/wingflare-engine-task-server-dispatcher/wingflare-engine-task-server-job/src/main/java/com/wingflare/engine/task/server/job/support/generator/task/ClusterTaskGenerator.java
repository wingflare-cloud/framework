package com.wingflare.engine.task.server.job.support.generator.task;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.JobArgsTypeEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.model.JobArgsHolder;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.dto.InstanceSelectCondition;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.util.ClientInfoUtils;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author opensnail
 * @date 2023-10-02 12:59:53
 * @since 2.4.0
 */
@Component
public class ClusterTaskGenerator extends AbstractJobTaskGenerator {
    private static final String TASK_NAME ="CLUSTER_TASK";
    private final InstanceManager instanceManager;
    private final JobTaskMapper jobTaskMapper;

    public ClusterTaskGenerator(InstanceManager instanceManager, JobTaskMapper jobTaskMapper) {
        this.instanceManager = instanceManager;
        this.jobTaskMapper = jobTaskMapper;
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.CLUSTER;
    }

    @Override
    public List<JobTask> doGenerate(JobTaskGenerateContext context) {
        // 生成可执行任务
        InstanceSelectCondition condition = Builder.of(InstanceSelectCondition::new)
                .with(InstanceSelectCondition::setAllocKey, String.valueOf(context.getJobId()))
                .with(InstanceSelectCondition::setGroupName, context.getGroupName())
                .with(InstanceSelectCondition::setNamespaceId, context.getNamespaceId())
                .with(InstanceSelectCondition::setRouteKey, context.getRouteKey())
                .with(InstanceSelectCondition::setTargetLabels, context.getLabels())
                .build();

        InstanceLiveInfo routeKey = instanceManager.getALiveInstanceByRouteKey(condition);
        if (Objects.isNull(routeKey)) {
            SnailJobLog.LOCAL.error("No executable client information. Job ID:[{}]", context.getJobId());
            return Lists.newArrayList();
        }

        // 新增任务实例
        JobTask jobTask = JobTaskConverter.INSTANCE.toJobTaskInstance(context);
        jobTask.setClientInfo(ClientInfoUtils.generate(routeKey.getNodeInfo()));
        jobTask.setArgsType(JobArgsTypeEnum.JSON.getArgsType());
        JobArgsHolder jobArgsHolder = new JobArgsHolder();
        jobArgsHolder.setJobParams(context.getArgsStr());
        jobTask.setArgsStr(JsonUtil.toJsonString(jobArgsHolder));
        jobTask.setTaskStatus(JobTaskStatusEnum.RUNNING.getStatus());
        jobTask.setTaskName(TASK_NAME);
        jobTask.setResultMessage(Optional.ofNullable(jobTask.getResultMessage()).orElse(StrUtil.EMPTY));
        Assert.isTrue(1 == jobTaskMapper.insert(jobTask), () -> new SnailJobServerException("Adding new task instance failed"));

        return Lists.newArrayList(jobTask);
    }

}
