package com.wingflare.facade.module.base.event;

import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.dto.DictDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 字典创建成功事件
 *
 */
public class DictCreatedEvent extends ApplicationEvent {

    protected transient DictDTO source;

    protected transient DictBO target;

    public DictCreatedEvent(DictDTO source) {
        super(source);
        this.source = source;
    }

    public DictCreatedEvent(DictBO target, DictDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public DictCreatedEvent(DictBO target, DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public DictCreatedEvent(DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public DictDTO getSource() {
        return source;
    }

    public DictBO getTarget() {
        return target;
    }

}
