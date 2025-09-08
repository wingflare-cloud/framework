package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户更新成功事件
 *
 */
public class UserUpdatedEvent extends BaseEvent {

    protected transient UserDTO source;

    protected transient UserDTO target;

    public UserUpdatedEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserUpdatedEvent(UserDTO target, UserDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public UserUpdatedEvent(UserDTO target, UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public UserUpdatedEvent(UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserDTO getSource() {
        return source;
    }

    public UserDTO getTarget() {
        return target;
    }
}
