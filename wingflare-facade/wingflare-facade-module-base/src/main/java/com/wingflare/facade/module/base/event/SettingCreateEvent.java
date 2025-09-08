package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.dto.SettingDTO;

import java.time.Clock;

/**
 * 设置创建事件
 */
public class SettingCreateEvent extends BaseEvent {

    protected transient SettingDTO source;

    protected transient SettingBO target;

    public SettingCreateEvent(SettingDTO source) {
        super(source);
        this.source = source;
    }

    public SettingCreateEvent(SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public SettingCreateEvent(SettingBO target, SettingDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public SettingCreateEvent(SettingBO target, SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public SettingDTO getSource() {
        return source;
    }

    public SettingBO getTarget() {
        return target;
    }

}
