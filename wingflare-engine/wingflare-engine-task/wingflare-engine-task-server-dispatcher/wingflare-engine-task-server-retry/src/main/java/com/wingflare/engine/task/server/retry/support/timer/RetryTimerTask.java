package com.wingflare.engine.task.server.retry.support.timer;

import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RetryTaskExecuteDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import io.netty.util.Timeout;
import org.apache.pekko.actor.ActorRef;

import java.text.MessageFormat;

/**
 * @author: opensnail
 * @date : 2023-09-22 17:09
 */
public class RetryTimerTask extends AbstractTimerTask {
    public static final String IDEMPOTENT_KEY_PREFIX = "retry_task_{0}";

    private final RetryTimerContext context;

    @Override
    public void doRun(final Timeout timeout) {

        RetryTaskExecuteDTO taskExecuteDTO =  RetryTaskConverter.INSTANCE.toRetryTaskExecuteDTO(context);
        // 执行阶段
        ActorRef actorRef = ActorGenerator.retryTaskExecutorActor();
        actorRef.tell(taskExecuteDTO, actorRef);

    }

    public RetryTimerTask(RetryTimerContext context) {
        this.context = context;
        super.retryId = context.getRetryId();
        super.retryTaskId = context.getRetryTaskId();
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, context.getRetryTaskId());
    }
}
