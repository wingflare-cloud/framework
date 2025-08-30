package com.wingflare.engine.task.server.starter.listener;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.util.SnailJobVersion;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import org.slf4j.helpers.MessageFormatter;
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
            SnailJobLog.LOCAL.info("snail-job server already started v{}", SnailJobVersion.getVersion());
            return;
        }

        System.out.println(MessageFormatter.format(SystemConstants.LOGO, SnailJobVersion.getVersion()).getMessage());
        SnailJobLog.LOCAL.info("snail-job server is preparing to start... v{}", SnailJobVersion.getVersion());
        lifecycleList.forEach(Lifecycle::start);
        SnailJobLog.LOCAL.info("snail-job server started successfully v{}", SnailJobVersion.getVersion());
        isStarted = true;
    }
}
