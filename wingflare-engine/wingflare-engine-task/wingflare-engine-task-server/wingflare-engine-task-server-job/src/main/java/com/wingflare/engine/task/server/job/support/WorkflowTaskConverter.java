package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.server.common.dto.WorkflowAlarmInfo;
import com.wingflare.engine.task.server.job.dto.WorkflowPartitionTaskDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.block.workflow.WorkflowBlockStrategyContext;
import com.wingflare.engine.task.server.job.support.executor.workflow.WorkflowExecutorContext;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGeneratorContext;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowTaskBatchGeneratorContext;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
import com.wingflare.task.datasource.template.persistence.po.WorkflowNode;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiaowoniu
 * @date 2023-12-21 22:04:19
 * @since 2.6.0
 */
@Mapper
public interface WorkflowTaskConverter {
    WorkflowTaskConverter INSTANCE = Mappers.getMapper(WorkflowTaskConverter.class);

    List<WorkflowPartitionTaskDTO> toWorkflowPartitionTaskList(List<Workflow> workflowList);

    @Mappings(
            @Mapping(source = "id", target = "workflowId")
    )
    WorkflowTaskPrepareDTO toWorkflowTaskPrepareDTO(WorkflowPartitionTaskDTO workflowPartitionTaskDTO);

    @Mappings(
            @Mapping(source = "id", target = "workflowId")
    )
    WorkflowTaskPrepareDTO toWorkflowTaskPrepareDTO(Workflow workflow);

    WorkflowTaskBatchGeneratorContext toWorkflowTaskBatchGeneratorContext(WorkflowTaskPrepareDTO workflowTaskPrepareDTO);

    WorkflowTaskBatch toWorkflowTaskBatch(WorkflowTaskBatchGeneratorContext context);

    JobTaskBatchGeneratorContext toJobTaskBatchGeneratorContext(WorkflowExecutorContext context);

    @Mappings(
            @Mapping(source = "id", target = "workflowNodeId")
    )
    WorkflowExecutorContext toWorkflowExecutorContext(WorkflowNode workflowNode);

    WorkflowTaskBatchGeneratorContext toWorkflowTaskBatchGeneratorContext(WorkflowBlockStrategyContext context);

    WorkflowBlockStrategyContext toWorkflowBlockStrategyContext(WorkflowTaskPrepareDTO prepareDTO);

    List<WorkflowAlarmInfo> toWorkflowTaskFailAlarmEventDTO(List<WorkflowTaskFailAlarmEventDTO> workflowTaskFailAlarmEventDTOList);

    @Mappings(
            @Mapping(source = "workflowTaskBatchId", target = "id")
    )
    WorkflowAlarmInfo toWorkflowTaskFailAlarmEventDTO(WorkflowTaskFailAlarmEventDTO workflowTaskFailAlarmEventDTO);
}
