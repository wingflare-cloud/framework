package com.wingflare.engine.task.common.core.enums;


import com.wingflare.engine.task.common.core.exception.TaskCommonException;

/**
 * 阻塞策略针对处于待处理 or 运行中的重试任务做了一种异常容错策略
 *
 * @author: xiaowoniu
 * @date : 2024-01-18
 * @since : 2.6.0
 */
public enum RetryBlockStrategyEnum {

    /**
     * 不创建新的重试任务，等待当前重试任务执行完成
     */
    DISCARD(1),
    /**
     * 停止当前的重试任务，然后新增一个新的重试任务
     */
    OVERLAY(2),
    /**
     * 每次都创建一个新的重试任务
     */
    CONCURRENCY(3),
    ;

    private final int blockStrategy;

    RetryBlockStrategyEnum(int blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public int getBlockStrategy() {
        return blockStrategy;
    }

    public static RetryBlockStrategyEnum valueOf(int blockStrategy) {
        for (final RetryBlockStrategyEnum value : RetryBlockStrategyEnum.values()) {
            if (value.blockStrategy == blockStrategy) {
                return value;
            }
        }

        throw new TaskCommonException("Incompatible blocking strategy. blockStrategy:[{}]", blockStrategy);
    }

}
