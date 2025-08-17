package com.wingflare.facade.module.base.event;

import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.dto.DictDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 字典更新成功事件
 *
 */
public class DictUpdatedEvent extends ApplicationEvent {

    protected transient DictDTO source;

    protected transient DictDTO target;

    public DictUpdatedEvent(DictDTO source) {
        super(source);
        this.source = source;
    }

    public DictUpdatedEvent(DictDTO target, DictDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public DictUpdatedEvent(DictDTO target, DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public DictUpdatedEvent(DictDTO source, Clock clock) {
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
