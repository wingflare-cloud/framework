package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.UserDTO;

import java.time.Clock;

/**
 * 用户删除成功事件
 *
 */
public class UserDeletedEvent extends BaseEvent {

    protected transient UserDTO source;

    public UserDeletedEvent(UserDTO source) {
        super(source);
        this.source = source;
    }

    public UserDeletedEvent(UserDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserDTO getSource() {
        return source;
    }

}
