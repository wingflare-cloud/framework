package com.wingflare.engine.task.client.common.init;

import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.event.TaskClientClosedEvent;
import com.wingflare.engine.task.client.common.event.TaskClientClosingEvent;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.lib.container.Container;
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
public class TaskCloseListener implements ApplicationListener<ContextClosedEvent> {
    private final List<Lifecycle> lifecycleList;

    private final static Logger log = LoggerFactory.getLogger(TaskCloseListener.class);

    public TaskCloseListener(List<Lifecycle> lifecycleList) {
        this.lifecycleList = lifecycleList;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("wingflare-task client about to shutdown v{}", TaskVersion.getVersion());
        Container.get(EventPublisher.class).publishEvent(new TaskClientClosingEvent());
        lifecycleList.forEach(Lifecycle::close);
        Container.get(EventPublisher.class).publishEvent(new TaskClientClosedEvent());
        log.info("wingflare-task client closed successfully v{}", TaskVersion.getVersion());
    }
}
