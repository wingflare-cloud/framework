package com.wingflare.engine.task.server.retry.support.block;

import com.wingflare.engine.task.common.core.enums.RetryBlockStrategyEnum;
import com.wingflare.engine.task.server.retry.dto.RetryTaskGeneratorDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.generator.task.RetryTaskGeneratorHandler;
import org.springframework.stereotype.Component;

/**
 * @author: opensnail
 * @date : 2025-02-10
 * @since : sj_1.4.0
 */
@Component
public class ConcurrencyRetryBlockStrategy extends AbstracJobBlockStrategy {
    private final RetryTaskGeneratorHandler retryTaskGeneratorHandler;

    public ConcurrencyRetryBlockStrategy(RetryTaskGeneratorHandler retryTaskGeneratorHandler) {
        this.retryTaskGeneratorHandler = retryTaskGeneratorHandler;
    }

    @Override
    public void doBlock(final BlockStrategyContext context) {
        // 重新生成任务
        RetryTaskGeneratorDTO generatorDTO = RetryTaskConverter.INSTANCE.toRetryTaskGeneratorDTO(context);
        retryTaskGeneratorHandler.generateRetryTask(generatorDTO);
    }

    @Override
    protected RetryBlockStrategyEnum blockStrategyEnum() {
        return RetryBlockStrategyEnum.CONCURRENCY;
    }
}
