package com.wingflare.engine.task.client.core.handler.add;

import com.wingflare.engine.task.client.core.enums.AllocationAlgorithmEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;

/**
 * @author opensnail
 * @date 2024-10-19 12:25:49
 * @since sj_1.2.0
 */
public class ShardingAddHandler extends AddHandler<ShardingAddHandler> {

    public ShardingAddHandler() {
        this(JobTaskTypeEnum.SHARDING);
    }

    public ShardingAddHandler(JobTaskTypeEnum taskType) {
        super(taskType);
        setRouteKey(AllocationAlgorithmEnum.ROUND);
        setR(this);
    }

    @Override
    public ShardingAddHandler addShardingArgs(String... shardingValue) {
        return super.addShardingArgs(shardingValue);
    }

    @Override
    public ShardingAddHandler setParallelNum(Integer parallelNum) {
        return super.setParallelNum(parallelNum);
    }
}
