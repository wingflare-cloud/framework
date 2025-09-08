package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.dto.SettingDTO;

import java.time.Clock;

/**
 * 设置删除成功事件
 */
public class SettingDeletedEvent extends BaseEvent {

    protected transient SettingDTO source;

    public SettingDeletedEvent(SettingDTO source) {
        super(source);
        this.source = source;
    }

    public SettingDeletedEvent(SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public SettingDTO getSource() {
        return source;
    }

}
