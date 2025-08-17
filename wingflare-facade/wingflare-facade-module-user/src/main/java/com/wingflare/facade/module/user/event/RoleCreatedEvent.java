package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.facade.module.user.dto.UserDTO;
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
        this.target = target;
    }

    public RoleCreatedEvent(RoleBO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public RoleCreatedEvent(UserDTO source, Clock clock) {
        super(source, clock);
    }

    public RoleDTO getSource() {
        return source;
    }

    public RoleBO getTarget() {
        return target;
    }
}
