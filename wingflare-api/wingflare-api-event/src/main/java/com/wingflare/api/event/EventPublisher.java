package com.wingflare.api.event;

import java.util.EventObject;

/**
 * 事件发布器
 */
public interface EventPublisher {

    void publishEvent(BaseEvent event);

    void publishEvent(EventObject event);

    void publishEvent(Object event);

}
