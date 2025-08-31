package com.wingflare.engine.task.server.starter.listener;


import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统启动监听器
 *
 * @author: opensnail
 * @date : 2021-11-19 19:00
 */
@Component
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {
    private final List<Lifecycle> lifecycleList;
    private volatile boolean isStarted = false;

    public StartListener(List<Lifecycle> lifecycleList) {
        this.lifecycleList = lifecycleList;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isStarted) {
            TaskEngineLog.LOCAL.info("wingflare-task server already started v{}", TaskVersion.getVersion());
            return;
        }

        TaskEngineLog.LOCAL.info("wingflare-task server is preparing to start... v{}", TaskVersion.getVersion());
        lifecycleList.forEach(Lifecycle::start);
        TaskEngineLog.LOCAL.info("wingflare-task server started successfully v{}", TaskVersion.getVersion());
        isStarted = true;
    }
}
