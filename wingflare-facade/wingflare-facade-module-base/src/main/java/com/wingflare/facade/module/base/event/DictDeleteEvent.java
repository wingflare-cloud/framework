package com.wingflare.facade.module.base.event;

import com.wingflare.facade.module.base.dto.DictDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 字典删除事件
 *
 */
public class DictDeleteEvent extends ApplicationEvent {

    protected transient DictDTO source;

    public DictDeleteEvent(DictDTO source) {
        super(source);
    }

    public DictDeleteEvent(DictDTO source, Clock clock) {
        super(source, clock);
    }

    public DictDTO getSource() {
        return source;
    }

}
