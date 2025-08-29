package com.wingflare.engine.task.client.core.handler.update;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;

public class ShardingUpdateHandler extends UpdateHandler<ShardingUpdateHandler>{

    public ShardingUpdateHandler(Long jobId) {
        super(JobTaskTypeEnum.SHARDING, jobId);
        setR(this);
    }

    @Override
    public ShardingUpdateHandler addShardingArgs(String... shardingValue) {
        return super.addShardingArgs(shardingValue);
    }

    @Override
    public ShardingUpdateHandler setParallelNum(Integer parallelNum) {
        return super.setParallelNum(parallelNum);
    }
}
