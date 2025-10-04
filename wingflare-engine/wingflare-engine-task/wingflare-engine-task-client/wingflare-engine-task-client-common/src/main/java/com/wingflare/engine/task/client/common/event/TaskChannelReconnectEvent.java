package com.wingflare.engine.task.client.common.event;

import com.wingflare.api.event.BaseEvent;

/**
 * @author xiaowoniu
 * @date 2024-03-14 21:17:55
 * @since 3.1.1
 */
public class TaskChannelReconnectEvent extends BaseEvent {
    private static final String SOURCE = "ChannelReconnect";

    public TaskChannelReconnectEvent() {
        super(SOURCE);
    }
}
