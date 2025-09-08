package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.RoleDTO;

import java.time.Clock;

/**
 * 角色更新成功事件
 *
 */
public class RoleUpdatedEvent extends BaseEvent {

    protected transient RoleDTO source;

    protected transient RoleDTO target;

    public RoleUpdatedEvent(RoleDTO source) {
        super(source);
        this.source = source;
    }

    public RoleUpdatedEvent(RoleDTO target, RoleDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public RoleUpdatedEvent(RoleDTO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public RoleUpdatedEvent(RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public RoleDTO getSource() {
        return source;
    }

    public RoleDTO getTarget() {
        return target;
    }
}
