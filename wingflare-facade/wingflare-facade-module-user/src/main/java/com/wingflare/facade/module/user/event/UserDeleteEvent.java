package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户删除事件
 *
 */
public class UserDeleteEvent extends BaseEvent {

    protected transient UserDTO source;

    public UserDeleteEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserDeleteEvent(UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserDTO getSource() {
        return source;
    }

}
