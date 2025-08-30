package com.wingflare.engine.task.server.retry.support.block;

import com.wingflare.engine.task.common.core.enums.RetryBlockStrategyEnum;
import com.wingflare.engine.task.server.retry.support.BlockStrategy;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author: opensnail
 * @date : 2025-02-10
 * @since : sj_1.4.0
 */
public abstract class AbstracJobBlockStrategy implements BlockStrategy, InitializingBean {

    @Override
    public void block(final BlockStrategyContext context) {
        doBlock(context);
    }

    protected abstract void doBlock(final BlockStrategyContext context);

    protected abstract RetryBlockStrategyEnum blockStrategyEnum();

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryBlockStrategyFactory.registerBlockStrategy(blockStrategyEnum(), this);
    }
}
