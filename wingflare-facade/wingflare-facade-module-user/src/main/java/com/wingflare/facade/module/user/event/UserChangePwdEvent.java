package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 用户密码更改事件
 *
 */
public class UserChangePwdEvent extends ApplicationEvent {

    protected transient UserDTO source;

    public UserChangePwdEvent(UserDTO source) {
        super(source);
    }

    public UserChangePwdEvent(UserDTO source, Clock clock) {
        super(source, clock);
    }

    public UserDTO getSource() {
        return source;
    }

}
