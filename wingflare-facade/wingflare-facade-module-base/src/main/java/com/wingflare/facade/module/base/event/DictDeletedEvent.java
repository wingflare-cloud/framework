package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.dto.DictDTO;

import java.time.Clock;

/**
 * 字典删除成功事件
 *
 */
public class DictDeletedEvent extends BaseEvent {

    protected transient DictDTO source;

    public DictDeletedEvent(DictDTO source) {
        super(source);
        this.source = source;
    }

    public DictDeletedEvent(DictDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public DictDTO getSource() {
        return source;
    }

}
