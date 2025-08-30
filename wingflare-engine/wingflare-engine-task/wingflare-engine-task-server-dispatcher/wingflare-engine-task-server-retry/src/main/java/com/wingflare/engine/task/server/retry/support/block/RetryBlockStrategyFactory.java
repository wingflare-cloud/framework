package com.wingflare.engine.task.server.retry.support.block;

import com.wingflare.engine.task.common.core.enums.RetryBlockStrategyEnum;
import com.wingflare.engine.task.server.retry.support.BlockStrategy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: opensnail
 * @date : 2025-02-10
 * @since : sj_1.4.0
 */
public final class RetryBlockStrategyFactory {
    private static final ConcurrentHashMap<RetryBlockStrategyEnum, BlockStrategy> CACHE = new ConcurrentHashMap<>();

    private RetryBlockStrategyFactory() {
    }

    static void registerBlockStrategy(RetryBlockStrategyEnum jobBlockStrategyEnum, BlockStrategy blockStrategy) {
        CACHE.put(jobBlockStrategyEnum, blockStrategy);
    }

    public static BlockStrategy getBlockStrategy(Integer blockStrategy) {
        return CACHE.get(RetryBlockStrategyEnum.valueOf(blockStrategy));
    }

}
