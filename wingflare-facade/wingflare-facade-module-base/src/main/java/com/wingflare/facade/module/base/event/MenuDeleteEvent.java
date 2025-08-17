package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.MenuDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 菜单删除事件
 *
 */
public class MenuDeleteEvent extends ApplicationEvent {

    protected transient MenuDTO source;

    public MenuDeleteEvent(MenuDTO source) {
        super(source);
        this.source = source;
    }

    public MenuDeleteEvent(MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public MenuDTO getSource() {
        return source;
    }

}
