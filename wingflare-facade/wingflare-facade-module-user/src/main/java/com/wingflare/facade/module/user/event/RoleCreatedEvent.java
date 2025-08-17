package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色创建成功事件
 *
 */
public class RoleCreatedEvent extends ApplicationEvent {

    protected transient RoleDTO source;

    protected transient RoleBO target;

    public RoleCreatedEvent(RoleDTO source) {
        super(source);
    }

    public RoleCreatedEvent(RoleBO target, RoleDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public RoleCreatedEvent(RoleBO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public RoleCreatedEvent(RoleDTO source, Clock clock) {
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
