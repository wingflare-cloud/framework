package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.cache.CacheConsumerGroup;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.dto.PartitionTask;
import com.wingflare.engine.task.server.common.dto.ScanTask;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.util.PartitionTaskUtils;
import com.wingflare.engine.task.server.job.dto.WorkflowPartitionTaskDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowMapper;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.ActorRef;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaowoniu
 * @date 2023-12-21 21:15:29
 * @since 2.6.0
 */
@Component(ActorGenerator.SCAN_WORKFLOW_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScanWorkflowTaskActor extends AbstractActor {
    private final WorkflowMapper workflowMapper;
    private final SystemProperties systemProperties;
    private final GroupConfigMapper groupConfigMapper;

    public ScanWorkflowTaskActor(WorkflowMapper workflowMapper, SystemProperties systemProperties, GroupConfigMapper groupConfigMapper) {
        this.workflowMapper = workflowMapper;
        this.systemProperties = systemProperties;
        this.groupConfigMapper = groupConfigMapper;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ScanTask.class, config -> {

            try {
                doScan(config);
            } catch (Exception e) {
                SnailJobLog.LOCAL.error("Data scanner processing exception. [{}]", config, e);
            }

        }).build();
    }

    private void doScan(ScanTask scanTask) {
        PartitionTaskUtils.process(startId -> listAvailableWorkflows(startId, scanTask),
                this::processPartitionTasks, 0);
    }

    private void processPartitionTasks(List<? extends PartitionTask> partitionTasks) {
        List<Workflow> waitUpdateJobs = new ArrayList<>();
        List<WorkflowTaskPrepareDTO> waitExecWorkflows = new ArrayList<>();
        long now = DateUtils.toNowMilli();
        for (PartitionTask partitionTask : partitionTasks) {
            WorkflowPartitionTaskDTO workflowPartitionTaskDTO = (WorkflowPartitionTaskDTO) partitionTask;
            processWorkflow(workflowPartitionTaskDTO, waitUpdateJobs, waitExecWorkflows, now);
        }

        // 批量更新
        workflowMapper.updateBatchNextTriggerAtById(waitUpdateJobs);

        for (final WorkflowTaskPrepareDTO waitExecTask : waitExecWorkflows) {
            // 执行预处理阶段
            ActorRef actorRef = ActorGenerator.workflowTaskPrepareActor();
            waitExecTask.setTaskExecutorScene(JobTaskExecutorSceneEnum.AUTO_WORKFLOW.getType());
            actorRef.tell(waitExecTask, actorRef);
        }
    }

    private void processWorkflow(WorkflowPartitionTaskDTO partitionTask, List<Workflow> waitUpdateWorkflows,
                                 List<WorkflowTaskPrepareDTO> waitExecJobs, long now) {
        CacheConsumerGroup.addOrUpdate(partitionTask.getGroupName(), partitionTask.getNamespaceId());

        // 更新下次触发时间
        Long nextTriggerAt = calculateNextTriggerTime(partitionTask, now);

        Workflow workflow = new Workflow();
        workflow.setId(partitionTask.getId());
        workflow.setNextTriggerAt(nextTriggerAt);
        waitUpdateWorkflows.add(workflow);

        waitExecJobs.add(WorkflowTaskConverter.INSTANCE.toWorkflowTaskPrepareDTO(partitionTask));

    }

    private Long calculateNextTriggerTime(WorkflowPartitionTaskDTO partitionTask, long now) {

        long nextTriggerAt = partitionTask.getNextTriggerAt();
        if ((nextTriggerAt + DateUtils.toEpochMilli(SystemConstants.SCHEDULE_PERIOD)) < now) {
            long randomMs = (long) (RandomUtil.randomDouble(0, 4, 2, RoundingMode.UP) * 1000);
            nextTriggerAt = now + randomMs;
            partitionTask.setNextTriggerAt(nextTriggerAt);
        }

        // 更新下次触发时间
        WaitStrategy waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(partitionTask.getTriggerType());
        WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();
        waitStrategyContext.setTriggerInterval(partitionTask.getTriggerInterval());
        waitStrategyContext.setNextTriggerAt(nextTriggerAt);

        return waitStrategy.computeTriggerTime(waitStrategyContext);
    }

    private List<WorkflowPartitionTaskDTO> listAvailableWorkflows(Long startId, ScanTask scanTask) {
        if (CollUtil.isEmpty(scanTask.getBuckets())) {
            return Collections.emptyList();
        }

        List<Workflow> workflows = workflowMapper.selectPage(new PageDTO<>(0, systemProperties.getJobPullPageSize(), Boolean.FALSE),
                new LambdaQueryWrapper<Workflow>()
                        .select(Workflow::getId, Workflow::getGroupName, Workflow::getNextTriggerAt, Workflow::getTriggerType,
                                Workflow::getTriggerInterval, Workflow::getExecutorTimeout, Workflow::getNamespaceId,
                                Workflow::getFlowInfo, Workflow::getBlockStrategy, Workflow::getWfContext)
                        .eq(Workflow::getWorkflowStatus, StatusEnum.YES.getStatus())
                        .eq(Workflow::getDeleted, StatusEnum.NO.getStatus())
                        .in(Workflow::getBucketIndex, scanTask.getBuckets())
                        .le(Workflow::getNextTriggerAt, DateUtils.toNowMilli() + DateUtils.toEpochMilli(SystemConstants.SCHEDULE_PERIOD))
                        .ge(Workflow::getId, startId)
                        .orderByAsc(Workflow::getId)
        ).getRecords();

        // 过滤已关闭的组
        if (CollUtil.isNotEmpty(workflows)) {
            List<String> groupConfigs = StreamUtils.toList(groupConfigMapper.selectList(new LambdaQueryWrapper<GroupConfig>()
                            .select(GroupConfig::getGroupName)
                            .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
                            .in(GroupConfig::getGroupName, StreamUtils.toSet(workflows, Workflow::getGroupName))),
                    GroupConfig::getGroupName);
            workflows = workflows.stream().filter(workflow -> groupConfigs.contains(workflow.getGroupName())).collect(Collectors.toList());
        }

        return WorkflowTaskConverter.INSTANCE.toWorkflowPartitionTaskList(workflows);
    }
}
