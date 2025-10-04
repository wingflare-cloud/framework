package com.wingflare.engine.task.client.common.event;

import com.wingflare.api.event.BaseEvent;

/**
 * @author xiaowoniu
 * @date 2024-03-14 21:23:29
 * @since 3.1.0
 */
public class TaskClientStartingEvent extends BaseEvent {
    private static final String SOURCE = "TaskClientStarting";

    public TaskClientStartingEvent() {
        super(SOURCE);
    }
}
