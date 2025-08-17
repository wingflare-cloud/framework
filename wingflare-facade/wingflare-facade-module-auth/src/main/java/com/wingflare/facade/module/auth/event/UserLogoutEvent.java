package com.wingflare.facade.module.auth.event;

import com.wingflare.lib.standard.model.UserAuth;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 用户登出事件
 */
public class UserLogoutEvent extends ApplicationEvent {

    protected transient UserAuth source;

    public UserLogoutEvent(UserAuth source) {
        super(source);
    }

    public UserLogoutEvent(UserAuth source, Clock clock) {
        super(source, clock);
    }

    public UserAuth getSource() {
        return source;
    }

}
