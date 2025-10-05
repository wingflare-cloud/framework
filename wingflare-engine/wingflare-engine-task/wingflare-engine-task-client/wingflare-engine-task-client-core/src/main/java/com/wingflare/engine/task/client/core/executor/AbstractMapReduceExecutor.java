package com.wingflare.engine.task.client.core.executor;

import cn.hutool.core.lang.Assert;
import com.wingflare.api.task.JobArgs;
import com.wingflare.api.task.MergeReduceArgs;
import com.wingflare.api.task.ReduceArgs;
import com.wingflare.engine.task.common.core.enums.MapReduceStageEnum;
import com.wingflare.engine.task.common.core.exception.TaskMapReduceException;
import com.wingflare.engine.task.common.core.model.JobContext;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;

/**
 * @author zhengweilin
 * @version 1.0.0
 * @date 2024/06/12
 */
public abstract class AbstractMapReduceExecutor extends AbstractMapExecutor {

    @Override
    public ExecuteResult doJobExecute(final JobArgs jobArgs) {
        JobContext jobContext = JobContextManager.getJobContext();
        Assert.notNull(jobContext.getMrStage(), "Please confirm that the current scheduled task type on the server is MapReduce");
        if (jobContext.getMrStage().equals(MapReduceStageEnum.MAP.getStage())) {
            return super.doJobExecute(jobArgs);
        } else if (jobContext.getMrStage().equals(MapReduceStageEnum.REDUCE.getStage())) {
            ReduceArgs reduceArgs = (ReduceArgs) jobArgs;
            return this.doReduceExecute(reduceArgs);
        } else if (jobContext.getMrStage().equals(MapReduceStageEnum.MERGE_REDUCE.getStage())) {
            MergeReduceArgs reduceArgs = (MergeReduceArgs) jobArgs;
            return this.doMergeReduceExecute(reduceArgs);
        }

        throw new TaskMapReduceException("Invalid MapReduceStage");
    }

    protected abstract ExecuteResult doReduceExecute(ReduceArgs reduceArgs);

    protected abstract ExecuteResult doMergeReduceExecute(MergeReduceArgs mergeReduceArgs);
}
