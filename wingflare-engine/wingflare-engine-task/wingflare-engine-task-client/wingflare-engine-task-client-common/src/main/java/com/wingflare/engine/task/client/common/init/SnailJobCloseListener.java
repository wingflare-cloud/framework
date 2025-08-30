package com.wingflare.engine.task.client.common.init;

import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.event.SnailClientClosedEvent;
import com.wingflare.engine.task.client.common.event.SnailClientClosingEvent;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统关闭监听器
 *
 * @author: opensnail
 * @date : 2021-11-19 19:00
 * @since 1.0.0
 */
@Component
public class SnailJobCloseListener implements ApplicationListener<ContextClosedEvent> {
    private final List<Lifecycle> lifecycleList;

    private final static Logger log = LoggerFactory.getLogger(SnailJobCloseListener.class);

    public SnailJobCloseListener(List<Lifecycle> lifecycleList) {
        this.lifecycleList = lifecycleList;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("wingflare-task client about to shutdown v{}", TaskVersion.getVersion());
        SnailSpringContext.getContext().publishEvent(new SnailClientClosingEvent());
        lifecycleList.forEach(Lifecycle::close);
        SnailSpringContext.getContext().publishEvent(new SnailClientClosedEvent());
        log.info("wingflare-task client closed successfully v{}", TaskVersion.getVersion());
    }
}
