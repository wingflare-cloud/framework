package com.wingflare.api.event;

import java.io.Serial;
import java.time.Clock;
import java.util.EventObject;

/**
 * 基础事件
 */
public abstract class BaseEvent extends EventObject {

    @Serial
    private static final long serialVersionUID = 7099896608136981937L;
    private final long timestamp;

    public BaseEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public BaseEvent(Object source, Clock clock) {
        super(source);
        this.timestamp = clock.millis();
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

}
