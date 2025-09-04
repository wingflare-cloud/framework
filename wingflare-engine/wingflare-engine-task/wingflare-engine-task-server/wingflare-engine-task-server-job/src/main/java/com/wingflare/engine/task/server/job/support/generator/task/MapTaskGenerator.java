package com.wingflare.engine.task.server.job.support.generator.task;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.job.dto.MapReduceArgsStrDTO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2024-06-19
 * @since : sj_1.1.0
 */
@Component
public class MapTaskGenerator extends MapReduceTaskGenerator {

    public MapTaskGenerator(final JobTaskMapper jobTaskMapper,
                            final TransactionTemplate transactionTemplate,
                            final InstanceManager instanceManager) {
        super(jobTaskMapper, transactionTemplate, instanceManager);
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.MAP;
    }

    @Override
    protected List<JobTask> doGenerate(final JobTaskGenerateContext context) {
        return super.doGenerate(context);
    }

    @Override
    protected MapReduceArgsStrDTO getJobParams(JobTaskGenerateContext context) {
        // 这里复用map reduce的参数能力
        MapReduceArgsStrDTO mapReduceArgsStrDTO = new MapReduceArgsStrDTO();
        mapReduceArgsStrDTO.setArgsStr(context.getArgsStr());
        return mapReduceArgsStrDTO;
    }
}
