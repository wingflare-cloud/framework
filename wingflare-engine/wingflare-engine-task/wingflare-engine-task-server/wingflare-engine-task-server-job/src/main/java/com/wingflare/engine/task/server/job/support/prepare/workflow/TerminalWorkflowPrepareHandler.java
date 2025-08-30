package com.wingflare.engine.task.server.job.support.prepare.workflow;

import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowBatchGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: xiaowoniu
 * @date : 2023-12-22 08:59
 * @since : 2.6.0
 */
@Component
public class TerminalWorkflowPrepareHandler extends AbstractWorkflowPrePareHandler {
    private static final Logger log = LoggerFactory.getLogger(TerminalWorkflowPrepareHandler.class);
    private final WorkflowBatchGenerator workflowBatchGenerator;

    public TerminalWorkflowPrepareHandler(WorkflowBatchGenerator workflowBatchGenerator) {
        this.workflowBatchGenerator = workflowBatchGenerator;
    }

    @Override
    public boolean matches(final Integer status) {
        return Objects.isNull(status);
    }

    @Override
    protected void doHandler(final WorkflowTaskPrepareDTO jobPrepareDTO) {
        log.debug("No workflow data being processed. Workflow ID:[{}]", jobPrepareDTO.getWorkflowId());
        workflowBatchGenerator.generateJobTaskBatch(WorkflowTaskConverter.INSTANCE.toWorkflowTaskBatchGeneratorContext(jobPrepareDTO));
    }
}
