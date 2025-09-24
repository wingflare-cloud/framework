package com.wingflare.engine.task.client.common.event;

import com.wingflare.api.event.BaseEvent;

/**
 * @author: opensnail
 * @date : 2024-04-15
 * @since : 3.3.0
 */
public class TaskServerStartFailedEvent extends BaseEvent {

    private static final String SOURCE = "SnailServerStartFailed";

    public TaskServerStartFailedEvent() {
        super(SOURCE);
    }

}
