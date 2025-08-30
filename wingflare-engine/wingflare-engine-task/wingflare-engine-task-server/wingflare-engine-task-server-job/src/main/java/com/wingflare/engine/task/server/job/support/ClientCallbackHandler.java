package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.job.support.callback.ClientCallbackContext;

/**
 * @author opensnail
 * @date 2023-10-03 23:10:50
 * @since 2.4.0
 */
public interface ClientCallbackHandler {

    JobTaskTypeEnum getTaskInstanceType();

    void callback(ClientCallbackContext context);
}
