package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.WorkflowPrePareHandler;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.pekko.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum.NOT_COMPLETE;

/**
 * @author xiaowoniu
 * @date 2023-12-21 22:41:29
 * @since 2.6.0
 */
@Component(ActorGenerator.WORKFLOW_TASK_PREPARE_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkflowTaskPrepareActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(WorkflowTaskPrepareActor.class);
    private final List<WorkflowPrePareHandler> workflowPrePareHandlers;
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;

    public WorkflowTaskPrepareActor(List<WorkflowPrePareHandler> workflowPrePareHandlers, WorkflowTaskBatchMapper workflowTaskBatchMapper) {
        this.workflowPrePareHandlers = workflowPrePareHandlers;
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(WorkflowTaskPrepareDTO.class, workflowTaskPrepareDTO -> {
            try {
                doPrepare(workflowTaskPrepareDTO);
            } catch (Exception e) {
                log.error("Preprocessing node exception", e);
            } finally {
                getContext().stop(getSelf());
            }
        }).build();
    }

    private void doPrepare(WorkflowTaskPrepareDTO workflowTaskPrepareDTO) {
        List<WorkflowTaskBatch> workflowTaskBatches = workflowTaskBatchMapper.selectList(new LambdaQueryWrapper<WorkflowTaskBatch>()
                .eq(WorkflowTaskBatch::getWorkflowId, workflowTaskPrepareDTO.getWorkflowId())
                .in(WorkflowTaskBatch::getTaskBatchStatus, NOT_COMPLETE));

        // 则直接创建一个任务批次
        if (CollUtil.isEmpty(workflowTaskBatches)) {
            for (WorkflowPrePareHandler workflowPrePareHandler : workflowPrePareHandlers) {
                // 终态任务
                if (workflowPrePareHandler.matches(null)) {
                    workflowPrePareHandler.handler(workflowTaskPrepareDTO);
                    break;
                }
            }
        } else {
            boolean onlyTimeoutCheck = false;
            for (WorkflowTaskBatch workflowTaskBatch : workflowTaskBatches) {
                workflowTaskPrepareDTO.setExecutionAt(workflowTaskBatch.getExecutionAt());
                workflowTaskPrepareDTO.setWorkflowTaskBatchId(workflowTaskBatch.getId());
                workflowTaskPrepareDTO.setOnlyTimeoutCheck(onlyTimeoutCheck);
                for (WorkflowPrePareHandler prePareHandler : workflowPrePareHandlers) {
                    if (prePareHandler.matches(workflowTaskBatch.getTaskBatchStatus())) {
                        prePareHandler.handler(workflowTaskPrepareDTO);
                        break;
                    }
                }

                // 当存在大量待处理任务时，除了第一个任务需要执行阻塞策略，其他任务只做任务检查
                onlyTimeoutCheck = true;
            }
        }

    }

}
