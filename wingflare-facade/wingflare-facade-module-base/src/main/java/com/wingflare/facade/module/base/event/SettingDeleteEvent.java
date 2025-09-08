package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.dto.SettingDTO;

import java.time.Clock;

/**
 * 设置删除事件
 */
public class SettingDeleteEvent extends BaseEvent {

    protected transient SettingDTO source;

    public SettingDeleteEvent(SettingDTO source) {
        super(source);
        this.source = source;
    }

    public SettingDeleteEvent(SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public SettingDTO getSource() {
        return source;
    }

}
