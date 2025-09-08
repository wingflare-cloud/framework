package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户更新事件
 *
 */
public class UserUpdateEvent extends BaseEvent {

    protected transient UserDTO source;

    protected transient UserDTO target;

    public UserUpdateEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserUpdateEvent(UserDTO target, UserDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public UserUpdateEvent(UserDTO target, UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public UserUpdateEvent(UserDTO source, Clock clock) {
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
