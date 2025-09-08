package com.wingflare.facade.module.base.event;


import com.wingflare.api.event.BaseEvent;
import com.wingflare.facade.module.base.dto.MenuDTO;

import java.time.Clock;

/**
 * 菜单更新事件
 *
 */
public class MenuUpdateEvent extends BaseEvent {

    protected transient MenuDTO source;

    protected transient MenuDTO target;

    public MenuUpdateEvent(MenuDTO source) {
        super(source);
        this.source = source;
    }

    public MenuUpdateEvent(MenuDTO target, MenuDTO source) {
        super(source);
        this.source = source;
        this.target = target;
    }

    public MenuUpdateEvent(MenuDTO target, MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
        this.target = target;
    }

    public MenuUpdateEvent(MenuDTO source, Clock clock) {
        super(source, clock);
        this.source = source;
    }

    public MenuDTO getSource() {
        return source;
    }

    public MenuDTO getTarget() {
        return target;
    }

}
