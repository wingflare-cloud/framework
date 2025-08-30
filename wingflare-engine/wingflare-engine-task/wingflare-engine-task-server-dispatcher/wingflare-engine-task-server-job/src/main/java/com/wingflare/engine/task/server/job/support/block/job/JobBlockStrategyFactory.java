package com.wingflare.engine.task.server.job.support.block.job;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.server.job.support.BlockStrategy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiaowoniu
 * @date : 2023-01-18
 * @since : 2.6.0
 */
public final class JobBlockStrategyFactory {
    private static final ConcurrentHashMap<JobBlockStrategyEnum, BlockStrategy> CACHE = new ConcurrentHashMap<>();

    private JobBlockStrategyFactory() {
    }

    static void registerBlockStrategy(JobBlockStrategyEnum jobBlockStrategyEnum, BlockStrategy blockStrategy) {
        CACHE.put(jobBlockStrategyEnum, blockStrategy);
    }

    public static BlockStrategy getBlockStrategy(Integer blockStrategy) {
        return CACHE.get(JobBlockStrategyEnum.valueOf(blockStrategy));
    }

}
