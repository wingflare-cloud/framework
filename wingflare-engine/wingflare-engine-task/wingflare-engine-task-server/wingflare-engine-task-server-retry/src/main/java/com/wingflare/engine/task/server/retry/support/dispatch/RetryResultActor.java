package com.wingflare.engine.task.server.retry.support.dispatch;

import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RetryExecutorResultDTO;
import com.wingflare.engine.task.server.retry.support.RetryResultHandler;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.result.RetryResultContext;
import org.apache.pekko.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-02
 */
@Component(ActorGenerator.RETRY_EXECUTOR_RESULT_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RetryResultActor extends AbstractActor {
    private final List<RetryResultHandler> retryResultHandlers;

    public RetryResultActor(List<RetryResultHandler> retryResultHandlers) {
        this.retryResultHandlers = retryResultHandlers;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RetryExecutorResultDTO.class, result -> {
            try {
                doResult(result);
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("Result processing exception. [{}]", result, e);
            } finally {
                getContext().stop(getSelf());
            }
        }).build();
    }

    private void doResult(RetryExecutorResultDTO result) {
        RetryResultContext context = RetryTaskConverter.INSTANCE.toRetryResultContext(result);
        for (RetryResultHandler retryResultHandler : retryResultHandlers) {
            if (retryResultHandler.supports(context)) {
                retryResultHandler.handle(context);
            }
        }
    }
}
