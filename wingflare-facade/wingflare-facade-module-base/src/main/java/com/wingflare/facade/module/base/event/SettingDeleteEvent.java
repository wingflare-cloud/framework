package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.SettingDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 设置删除事件
 */
public class SettingDeleteEvent extends ApplicationEvent {

    protected transient SettingDTO source;

    public SettingDeleteEvent(SettingDTO source) {
        super(source);
    }

    public SettingDeleteEvent(SettingDTO source, Clock clock) {
        super(source, clock);
    }

    public SettingDTO getSource() {
        return source;
    }

}
