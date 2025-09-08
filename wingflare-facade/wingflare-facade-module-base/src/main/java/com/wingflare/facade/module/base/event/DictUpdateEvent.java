package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.dto.DictDTO;

import java.time.Clock;

/**
 * 字典创建事件
 *
 */
public class DictUpdateEvent extends BaseEvent {

    protected transient DictDTO source;

    protected transient DictDTO target;

    public DictUpdateEvent(DictDTO source) {
        super(source);
        this.source = source;
    }

    public DictUpdateEvent(DictDTO target, DictDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public DictUpdateEvent(DictDTO target, DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public DictUpdateEvent(DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public DictDTO getSource() {
        return source;
    }

    public DictDTO getTarget() {
        return target;
    }

}
