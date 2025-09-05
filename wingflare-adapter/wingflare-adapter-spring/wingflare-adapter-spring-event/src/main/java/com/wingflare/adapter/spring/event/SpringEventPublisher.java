package com.wingflare.adapter.spring.event;

import com.wingflare.api.event.BaseEvent;
import com.wingflare.api.event.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

import java.util.EventObject;

/**
 * 事件发布者
 */
public class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher publisher;

    public SpringEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publishEvent(BaseEvent event) {
        publisher.publishEvent(event);
    }

    @Override
    public void publishEvent(EventObject event) {
        publisher.publishEvent(event);
    }

    @Override
    public void publishEvent(Object event) {
        publisher.publishEvent(event);
    }

}
