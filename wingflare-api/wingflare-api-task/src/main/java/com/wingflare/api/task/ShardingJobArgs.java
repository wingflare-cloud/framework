package com.wingflare.api.task;


/**
 * @author: opensnail
 * @date : 2023-10-18 16:53
 * @since : 2.4.0
 */
public class ShardingJobArgs extends JobArgs {

    private Integer shardingTotal;

    private Integer shardingIndex;

    public Integer getShardingTotal() {
        return shardingTotal;
    }

    public void setShardingTotal(Integer shardingTotal) {
        this.shardingTotal = shardingTotal;
    }

    public Integer getShardingIndex() {
        return shardingIndex;
    }

    public void setShardingIndex(Integer shardingIndex) {
        this.shardingIndex = shardingIndex;
    }
}
