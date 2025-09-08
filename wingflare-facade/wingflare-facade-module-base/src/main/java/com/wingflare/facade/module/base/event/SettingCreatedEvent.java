package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.dto.SettingDTO;

import java.time.Clock;

/**
 * 设置创建成功事件
 */
public class SettingCreatedEvent extends BaseEvent {

    protected transient SettingDTO source;

    protected transient SettingBO target;

    public SettingCreatedEvent(SettingDTO source) {
        super(source);
        this.source = source;
    }

    public SettingCreatedEvent(SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public SettingCreatedEvent(SettingBO target, SettingDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public SettingCreatedEvent(SettingBO target, SettingDTO source, Clock clock) {
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
