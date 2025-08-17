package com.wingflare.facade.module.base.event;


import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 菜单创建成功事件
 *
 */
public class MenuCreatedEvent extends ApplicationEvent {

    protected transient MenuDTO source;

    protected transient MenuBO target;

    public MenuCreatedEvent(MenuDTO source) {
        super(source);
    }

    public MenuCreatedEvent(MenuBO target, MenuDTO source) {
        super(source);
        this.target = target;
    }

    public MenuCreatedEvent(MenuBO target, MenuDTO source, Clock clock) {
        super(source, clock);
        this.target = target;
    }

    public MenuCreatedEvent(MenuDTO source, Clock clock) {
        super(source, clock);
    }

    public MenuDTO getSource() {
        return source;
    }

    public MenuBO getTarget() {
        return target;
    }

}
