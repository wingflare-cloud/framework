package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 用户更新事件
 *
 */
public class UserUpdateEvent extends ApplicationEvent {

    protected transient UserDTO source;

    protected transient UserDTO target;

    public UserUpdateEvent(UserDTO source) {
        super(source);
    }

    public UserUpdateEvent(UserDTO target, UserDTO source) {
        super(source);
        this.target = target;
    }

    public UserUpdateEvent(UserDTO target, UserDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public UserUpdateEvent(UserDTO source, Clock clock) {
        super(source, clock);
    }

    public UserDTO getSource() {
        return source;
    }

    public UserDTO getTarget() {
        return target;
    }
}
