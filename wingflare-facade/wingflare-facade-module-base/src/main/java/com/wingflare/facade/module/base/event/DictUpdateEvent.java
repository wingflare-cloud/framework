package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.DictDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 字典创建事件
 *
 */
public class DictUpdateEvent extends ApplicationEvent {

    protected transient DictDTO source;

    protected transient DictDTO target;

    public DictUpdateEvent(DictDTO source) {
        super(source);
    }

    public DictUpdateEvent(DictDTO target, DictDTO source) {
        super(source);
        this.target = target;
    }

    public DictUpdateEvent(DictDTO target, DictDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public DictUpdateEvent(DictDTO source, Clock clock) {
        super(source, clock);
    }

    public DictDTO getSource() {
        return source;
    }

    public DictDTO getTarget() {
        return target;
    }

}
