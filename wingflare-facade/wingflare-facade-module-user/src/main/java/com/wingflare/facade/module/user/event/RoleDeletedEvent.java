package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.dto.RoleDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色删除成功事件
 *
 */
public class RoleDeletedEvent extends ApplicationEvent {

    protected transient RoleDTO source;

    public RoleDeletedEvent(RoleDTO source) {
        super(source);
    }

    public RoleDeletedEvent(RoleDTO source, Clock clock) {
        super(source, clock);
    }

    public RoleDTO getSource() {
        return source;
    }

}
