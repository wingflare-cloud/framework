package com.wingflare.facade.module.auth.event;

import com.wingflare.lib.standard.model.UserAuth;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 用户登录事件
 *
 */
public class UserLoginEvent extends ApplicationEvent {

    protected transient UserAuth source;

    public UserLoginEvent(UserAuth source) {
        super(source);
    }

    public UserLoginEvent(UserAuth source, Clock clock) {
        super(source, clock);
    }

    public UserAuth getSource() {
        return source;
    }

}
