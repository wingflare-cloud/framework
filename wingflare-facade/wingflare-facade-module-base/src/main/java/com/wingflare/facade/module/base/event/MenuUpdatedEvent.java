package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.dto.MenuDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 菜单更新成功事件
 *
 */
public class MenuUpdatedEvent extends ApplicationEvent {

    protected transient MenuDTO source;

    protected transient MenuDTO target;

    public MenuUpdatedEvent(MenuDTO source) {
        super(source);
    }

    public MenuUpdatedEvent(MenuDTO target, MenuDTO source) {
        super(source);
        this.target = target;
    }

    public MenuUpdatedEvent(MenuDTO target, MenuDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public MenuUpdatedEvent(MenuDTO source, Clock clock) {
        super(source, clock);
    }

    public MenuDTO getSource() {
        return source;
    }

    public MenuDTO getTarget() {
        return target;
    }

}
