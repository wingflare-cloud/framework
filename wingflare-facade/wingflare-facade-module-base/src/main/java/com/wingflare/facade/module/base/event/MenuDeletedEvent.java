package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.MenuDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 菜单删除成功事件
 *
 */
public class MenuDeletedEvent extends ApplicationEvent {

    protected transient MenuDTO source;

    public MenuDeletedEvent(MenuDTO source) {
        super(source);
        this.source = source;
    }

    public MenuDeletedEvent(MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public MenuDTO getSource() {
        return source;
    }

}
