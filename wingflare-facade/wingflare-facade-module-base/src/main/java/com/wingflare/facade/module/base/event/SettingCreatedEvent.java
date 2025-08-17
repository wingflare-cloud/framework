package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 设置创建成功事件
 */
public class SettingCreatedEvent extends ApplicationEvent {

    protected transient SettingDTO source;

    protected transient SettingBO target;

    public SettingCreatedEvent(SettingDTO source) {
        super(source);
    }

    public SettingCreatedEvent(SettingDTO source, Clock clock) {
        super(source, clock);
    }

    public SettingCreatedEvent(SettingBO target, SettingDTO source) {
        super(source);
        this.target = target;
    }

    public SettingCreatedEvent(SettingBO target, SettingDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public SettingDTO getSource() {
        return source;
    }

    public SettingBO getTarget() {
        return target;
    }

}
