package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户创建成功事件
 *
 */
public class UserCreatedEvent extends BaseEvent {

    protected transient UserDTO source;

    protected transient UserBO target;

    public UserCreatedEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserCreatedEvent(UserBO target, UserDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public UserCreatedEvent(UserBO target, UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public UserCreatedEvent(UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserDTO getSource() {
        return source;
    }

    public UserBO getTarget() {
        return target;
    }
}
