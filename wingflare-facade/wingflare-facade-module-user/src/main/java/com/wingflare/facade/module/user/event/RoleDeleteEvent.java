package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.facade.module.user.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色删除事件
 *
 */
public class RoleDeleteEvent extends ApplicationEvent {

    protected transient RoleDTO source;

    public RoleDeleteEvent(RoleDTO source) {
        super(source);
        this.source = source;
    }

    public RoleDeleteEvent(RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public RoleDTO getSource() {
        return source;
    }

}
