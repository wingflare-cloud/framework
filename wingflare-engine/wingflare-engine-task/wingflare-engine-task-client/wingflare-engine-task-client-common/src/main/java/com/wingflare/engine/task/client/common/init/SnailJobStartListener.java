package com.wingflare.engine.task.client.common.init;

import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.common.event.SnailClientStartedEvent;
import com.wingflare.engine.task.client.common.event.SnailClientStartingEvent;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.log.SnailJobLog;
import org.slf4j.helpers.MessageFormatter;
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
public class SnailJobStartListener implements ApplicationRunner {
    private final List<Lifecycle> lifecycleList;
    private volatile boolean isStarted = false;

    public SnailJobStartListener(List<Lifecycle> lifecycleList) {
        this.lifecycleList = lifecycleList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isStarted) {
            SnailJobLog.LOCAL.info("snail-job client already started v{}", TaskVersion.getVersion());
            return;
        }

        System.out.println(MessageFormatter.format(SystemConstants.LOGO, TaskVersion.getVersion()).getMessage());
        SnailJobLog.LOCAL.info("snail-job client is preparing to start... v{}", TaskVersion.getVersion());
        SnailSpringContext.getContext().publishEvent(new SnailClientStartingEvent());
        lifecycleList.forEach(Lifecycle::start);
        SnailSpringContext.getContext().publishEvent(new SnailClientStartedEvent());
        isStarted = true;
        SnailJobLog.LOCAL.info("snail-job client started successfully v{}", TaskVersion.getVersion());
    }

}
