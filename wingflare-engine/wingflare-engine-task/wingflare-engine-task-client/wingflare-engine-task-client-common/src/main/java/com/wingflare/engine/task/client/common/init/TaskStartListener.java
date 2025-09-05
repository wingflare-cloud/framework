package com.wingflare.engine.task.client.common.init;

import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.event.TaskClientStartedEvent;
import com.wingflare.engine.task.client.common.event.TaskClientStartingEvent;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.container.Container;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统启动监听器
 *
 * @author: opensnail
 * @date : 2021-11-19 19:00
 */
@Component
public class TaskStartListener implements ApplicationRunner {
    private final List<Lifecycle> lifecycleList;
    private volatile boolean isStarted = false;

    public TaskStartListener(List<Lifecycle> lifecycleList) {
        this.lifecycleList = lifecycleList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isStarted) {
            TaskEngineLog.LOCAL.info("wingflare-task client already started v{}", TaskVersion.getVersion());
            return;
        }

        TaskEngineLog.LOCAL.info("wingflare-task client is preparing to start... v{}", TaskVersion.getVersion());
        Container.get(EventPublisher.class).publishEvent(new TaskClientStartingEvent());
        lifecycleList.forEach(Lifecycle::start);
        Container.get(EventPublisher.class).publishEvent(new TaskClientStartedEvent());
        isStarted = true;
        TaskEngineLog.LOCAL.info("wingflare-task client started successfully v{}", TaskVersion.getVersion());
    }

}
