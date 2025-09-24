package com.wingflare.engine.task.client.core.handler.trigger;


public class ShardingTriggerHandler extends TriggerJobHandler<ShardingTriggerHandler>{

    public ShardingTriggerHandler(Long triggerJobId) {
        super(triggerJobId);
    }

    @Override
    public ShardingTriggerHandler addShardingArgs(String... shardingValue) {
        return super.addShardingArgs(shardingValue);
    }
}
