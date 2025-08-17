package com.wingflare.facade.module.user.event;


import com.wingflare.facade.module.user.dto.IdentityDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 岗位身份删除成功事件
 *
 */
public class IdentityDeletedEvent extends ApplicationEvent {

    protected transient IdentityDTO source;

    public IdentityDeletedEvent(IdentityDTO source) {
        super(source);
        this.source = source;
    }

    public IdentityDeletedEvent(IdentityDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public IdentityDTO getSource() {
        return source;
    }

}
