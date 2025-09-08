package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.dto.MenuDTO;

import java.time.Clock;

/**
 * 菜单创建事件
 *
 */
public class MenuCreateEvent extends BaseEvent {

    protected transient MenuDTO source;

    protected transient MenuBO target;

    public MenuCreateEvent(MenuDTO source) {
        super(source);
        this.source = source;
    }

    public MenuCreateEvent(MenuBO target, MenuDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public MenuCreateEvent(MenuBO target, MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public MenuCreateEvent(MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public MenuDTO getSource() {
        return source;
    }

    public MenuBO getTarget() {
        return target;
    }

}
