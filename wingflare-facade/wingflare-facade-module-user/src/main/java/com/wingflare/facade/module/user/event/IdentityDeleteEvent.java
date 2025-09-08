package com.wingflare.facade.module.user.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.user.dto.IdentityDTO;

import java.time.Clock;

/**
 * 岗位身份删除事件
 *
 */
public class IdentityDeleteEvent extends BaseEvent {

    protected transient IdentityDTO source;

    public IdentityDeleteEvent(IdentityDTO source) {
        super(source);
        this.source = source;
    }

    public IdentityDeleteEvent(IdentityDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public IdentityDTO getSource() {
        return source;
    }

}
