package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色创建事件
 *
 */
public class RoleCreateEvent extends ApplicationEvent {

    protected transient RoleDTO source;

    protected transient RoleBO target;

    public RoleCreateEvent(RoleDTO source) {
        super(source);
        this.source = source;
    }

    public RoleCreateEvent(RoleBO target, RoleDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public RoleCreateEvent(RoleBO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public RoleCreateEvent(RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public RoleDTO getSource() {
        return source;
    }

    public RoleBO getTarget() {
        return target;
    }
}
