package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.facade.module.user.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色更新事件
 *
 */
public class RoleUpdateEvent extends ApplicationEvent {

    protected transient RoleDTO source;

    protected transient RoleDTO target;

    public RoleUpdateEvent(RoleDTO source) {
        super(source);
    }

    public RoleUpdateEvent(RoleDTO target, RoleDTO source) {
        super(source);
        this.target = target;
    }

    public RoleUpdateEvent(RoleDTO target, RoleDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public RoleUpdateEvent(UserDTO source, Clock clock) {
        super(source, clock);
    }

    public RoleDTO getSource() {
        return source;
    }

    public RoleDTO getTarget() {
        return target;
    }
}
