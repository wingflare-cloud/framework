package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 用户创建成功事件
 *
 */
public class UserCreatedEvent extends ApplicationEvent {

    protected transient UserDTO source;

    protected transient UserBO target;

    public UserCreatedEvent(UserDTO source) {
        super(source);
    }

    public UserCreatedEvent(UserBO target, UserDTO source) {
        super(source);
        this.target = target;
    }

    public UserCreatedEvent(UserBO target, UserDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public UserCreatedEvent(UserDTO source, Clock clock) {
        super(source, clock);
    }

    public UserDTO getSource() {
        return source;
    }

    public UserBO getTarget() {
        return target;
    }
}
