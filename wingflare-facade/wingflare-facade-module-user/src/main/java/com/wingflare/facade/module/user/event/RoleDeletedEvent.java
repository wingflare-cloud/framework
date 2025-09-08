package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.RoleDTO;

import java.time.Clock;

/**
 * 角色删除成功事件
 *
 */
public class RoleDeletedEvent extends BaseEvent {

    protected transient RoleDTO source;

    public RoleDeletedEvent(RoleDTO source) {
        super(source);
        this.source = source;
    }

    public RoleDeletedEvent(RoleDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public RoleDTO getSource() {
        return source;
    }

}
