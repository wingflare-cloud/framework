package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.SettingDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 设置更新成功事件
 */
public class SettingUpdatedEvent extends ApplicationEvent {

    protected transient SettingDTO source;

    protected transient SettingDTO target;

    public SettingUpdatedEvent(SettingDTO source) {
        super(source);
        this.source = source;
    }

    public SettingUpdatedEvent(SettingDTO source, Clock clock) {
        super(source, clock);
    }

    public SettingUpdatedEvent(SettingDTO target, SettingDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public SettingUpdatedEvent(SettingDTO target, SettingDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public SettingDTO getSource() {
        return source;
    }

    public SettingDTO getTarget() {
        return target;
    }

}
