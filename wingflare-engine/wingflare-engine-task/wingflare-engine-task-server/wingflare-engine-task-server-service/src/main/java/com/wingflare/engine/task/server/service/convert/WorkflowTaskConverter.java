package com.wingflare.engine.task.server.service.convert;

import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.task.datasource.template.persistence.po.Workflow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
@Mapper
public interface WorkflowTaskConverter {
    WorkflowTaskConverter INSTANCE = Mappers.getMapper(WorkflowTaskConverter.class);

    @Mappings(
            @Mapping(source = "id", target = "workflowId")
    )
    WorkflowTaskPrepareDTO toWorkflowTaskPrepareDTO(Workflow workflow);

}
