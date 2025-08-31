package com.wingflare.engine.task.client.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: opensnail
 * @date : 2024-04-15
 * @since : 3.3.0
 */
public class TaskServerStartFailedEvent extends ApplicationEvent {

    private static final String SOURCE = "SnailServerStartFailed";

    public TaskServerStartFailedEvent() {
        super(SOURCE);
    }

}
