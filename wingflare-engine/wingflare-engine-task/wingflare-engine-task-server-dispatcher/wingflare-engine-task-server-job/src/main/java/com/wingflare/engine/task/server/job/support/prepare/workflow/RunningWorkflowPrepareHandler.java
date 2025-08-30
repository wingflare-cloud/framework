package com.wingflare.engine.task.server.job.support.prepare.workflow;

import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.BlockStrategy;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.alarm.event.WorkflowTaskFailAlarmEvent;
import com.wingflare.engine.task.server.job.support.block.workflow.WorkflowBlockStrategyContext;
import com.wingflare.engine.task.server.job.support.block.workflow.WorkflowBlockStrategyFactory;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import com.wingflare.lib.core.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author xiaowoniu
 * @date 2023-12-23 23:09:07
 * @since 2.6.0
 */
@Component
public class RunningWorkflowPrepareHandler extends AbstractWorkflowPrePareHandler {

    private static final Logger log = LoggerFactory.getLogger(RunningWorkflowPrepareHandler.class);
    private final WorkflowBatchHandler workflowBatchHandler;

    public RunningWorkflowPrepareHandler(WorkflowBatchHandler workflowBatchHandler) {
        this.workflowBatchHandler = workflowBatchHandler;
    }

    @Override
    public boolean matches(Integer status) {
        return Objects.nonNull(status) && JobTaskBatchStatusEnum.RUNNING.getStatus() == status;
    }

    @Override
    protected void doHandler(WorkflowTaskPrepareDTO prepare) {
        log.debug("Running tasks exist. Prepare:[{}]", JsonUtil.toJsonString(prepare));


        // 1. 若DAG已经支持完成了，由于异常原因导致的没有更新成终态此次进行一次更新操作
        int blockStrategy = prepare.getBlockStrategy();
        if (workflowBatchHandler.complete(prepare.getWorkflowTaskBatchId())) {
            // 开启新的任务
            blockStrategy = JobBlockStrategyEnum.CONCURRENCY.getBlockStrategy();
        } else {
            // 计算超时时间
            long delay = DateUtils.toNowMilli() - prepare.getExecutionAt();

            // 2. 判断DAG是否已经支持超时
            // 计算超时时间，到达超时时间中断任务
            if (delay > DateUtils.toEpochMilli(prepare.getExecutorTimeout())) {

                // 超时停止任务
                workflowBatchHandler.stop(prepare.getWorkflowTaskBatchId(), JobOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());

                // 超时停止任务
                String reason = String.format("Task execution timeout. Workflow task batch ID:[%s] Delay:[%s] Executor timeout:[%s]", prepare.getWorkflowTaskBatchId(), delay, DateUtils.toEpochMilli(prepare.getExecutorTimeout()));
                SnailSpringContext.getContext().publishEvent(new WorkflowTaskFailAlarmEvent(Builder.of(WorkflowTaskFailAlarmEventDTO::new)
                        .with(WorkflowTaskFailAlarmEventDTO::setWorkflowTaskBatchId, prepare.getWorkflowTaskBatchId())
                        .with(WorkflowTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.WORKFLOW_TASK_ERROR.getNotifyScene())
                        .with(WorkflowTaskFailAlarmEventDTO::setReason, reason)
                        .build()));
                log.info(reason);
            }
        }

        // 仅是超时检测的，不执行阻塞策略
        if (prepare.isOnlyTimeoutCheck()) {
            return;
        }

        // 3. 支持阻塞策略同JOB逻辑一致
        BlockStrategy blockStrategyInterface = WorkflowBlockStrategyFactory.getBlockStrategy(blockStrategy);
        WorkflowBlockStrategyContext workflowBlockStrategyContext = WorkflowTaskConverter.INSTANCE.toWorkflowBlockStrategyContext(
                prepare);
        blockStrategyInterface.block(workflowBlockStrategyContext);

    }
}
