package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.SettingDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 设置更新事件
 */
public class SettingUpdateEvent extends ApplicationEvent {

    protected transient SettingDTO source;

    protected transient SettingDTO target;

    public SettingUpdateEvent(SettingDTO source) {
        super(source);
    }

    public SettingUpdateEvent(SettingDTO source, Clock clock) {
        super(source, clock);
    }

    public SettingUpdateEvent(SettingDTO target, SettingDTO source) {
        super(source);
        this.target = target;
    }

    public SettingUpdateEvent(SettingDTO target, SettingDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public SettingDTO getSource() {
        return source;
    }

    public SettingDTO getTarget() {
        return target;
    }

}
