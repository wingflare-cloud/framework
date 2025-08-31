package com.wingflare.engine.task.server.job.support.executor.job;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.job.support.JobExecutor;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author opensnail
 * @date 2023-10-03 22:13:04
 * @since 2.4.0
 */
public abstract class AbstractJobExecutor implements JobExecutor, InitializingBean {

    @Override
    public void execute(JobExecutorContext context) {
        if (CollUtil.isEmpty(context.getTaskList())) {
            TaskEngineLog.LOCAL.warn("List of tasks to be executed is empty. Task batch ID:[{}]", context.getTaskBatchId());
            return;
        }
        doExecute(context);
    }

    protected abstract void doExecute(JobExecutorContext context);

    @Override
    public void afterPropertiesSet() throws Exception {
        JobExecutorFactory.registerJobExecutor(getTaskInstanceType(), this);
    }
}
