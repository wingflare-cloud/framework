package com.wingflare.facade.module.auth.event;

import com.wingflare.api.event.BaseEvent;
import com.wingflare.lib.standard.model.UserAuth;

import java.time.Clock;

/**
 * 用户登录事件
 *
 */
public class UserLoginEvent extends BaseEvent {

    protected transient UserAuth source;

    public UserLoginEvent(UserAuth source) {
        super(source);
        this.source = source;
    }

    public UserLoginEvent(UserAuth source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public UserAuth getSource() {
        return source;
    }

}
