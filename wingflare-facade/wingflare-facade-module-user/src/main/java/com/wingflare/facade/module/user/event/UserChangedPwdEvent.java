package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户密码更改成功事件
 *
 */
public class UserChangedPwdEvent extends BaseEvent {

    protected transient UserDTO source;

    public UserChangedPwdEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserChangedPwdEvent(UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserDTO getSource() {
        return source;
    }

}
