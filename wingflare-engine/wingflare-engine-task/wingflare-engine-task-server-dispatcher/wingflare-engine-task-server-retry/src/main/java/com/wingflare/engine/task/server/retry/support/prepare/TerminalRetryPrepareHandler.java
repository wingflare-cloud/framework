package com.wingflare.engine.task.server.retry.support.prepare;

import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.support.RetryPrePareHandler;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.generator.task.RetryTaskGeneratorHandler;
import org.springframework.stereotype.Component;

import static com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum.TERMINAL_STATUS_SET;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
@Component
public class TerminalRetryPrepareHandler implements RetryPrePareHandler {
    private final RetryTaskGeneratorHandler retryTaskGeneratorHandler;

    public TerminalRetryPrepareHandler(RetryTaskGeneratorHandler retryTaskGeneratorHandler) {
        this.retryTaskGeneratorHandler = retryTaskGeneratorHandler;
    }

    @Override
    public boolean matches(Integer status) {
        return TERMINAL_STATUS_SET.contains(status);
    }

    @Override
    public void handle(RetryTaskPrepareDTO jobPrepareDTO) {
        retryTaskGeneratorHandler.generateRetryTask(RetryTaskConverter.INSTANCE.toRetryTaskGeneratorDTO(jobPrepareDTO));
    }
}
