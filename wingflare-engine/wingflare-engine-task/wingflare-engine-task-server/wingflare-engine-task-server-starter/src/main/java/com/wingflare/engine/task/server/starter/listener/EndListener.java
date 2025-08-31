package com.wingflare.engine.task.server.starter.listener;

import com.wingflare.engine.task.common.core.util.TaskVersion;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 关闭监听器
 *
 * @author: opensnail
 * @date : 2021-11-19 19:00
 */
@Component
public class EndListener implements ApplicationListener<ContextClosedEvent> {

    @Resource
    private List<Lifecycle> lifecycleList;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        TaskEngineLog.LOCAL.info("wingflare-task client about to shutdown v{}", TaskVersion.getVersion());
        lifecycleList.forEach(Lifecycle::close);
        TaskEngineLog.LOCAL.info("wingflare-task client closed successfully v{}", TaskVersion.getVersion());
    }
}
