package com.wingflare.engine.task.server.job.support.callback;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import org.springframework.stereotype.Component;

/**
 * @author opensnail
 * @date 2024-06-24 22:51:20
 * @since sj_1.1.0
 */
@Component
public class MapClientCallbackHandler extends MapReduceClientCallbackHandler {
    public MapClientCallbackHandler(JobTaskMapper jobTaskMapper, InstanceManager instanceManager) {
        super(jobTaskMapper, instanceManager);
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.MAP;
    }

    @Override
    protected void doCallback(ClientCallbackContext context) {
        super.doCallback(context);
    }
}
