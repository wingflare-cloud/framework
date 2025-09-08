package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.RoleDTO;

import java.time.Clock;

/**
 * 角色更新事件
 *
 */
public class RoleUpdateEvent extends BaseEvent {

    protected transient RoleDTO source;

    protected transient RoleDTO target;

    public RoleUpdateEvent(RoleDTO source) {
        super(source);
        this.source = source;
    }

    public RoleUpdateEvent(RoleDTO target, RoleDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public RoleUpdateEvent(RoleDTO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public RoleUpdateEvent(RoleDTO source, Clock clock) {
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
