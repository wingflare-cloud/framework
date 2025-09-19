package com.wingflare.adapter.spring.common;


import com.wingflare.api.lifecycle.ServerStopException;
import com.wingflare.lib.lifecycle.LifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

/**
 * 优雅启停
 */
public class AppSmartLifecycle implements SmartLifecycle {

    private static final Logger logger = LoggerFactory.getLogger(AppSmartLifecycle.class);

    private final LifecycleManager lifecycleManager;

    public AppSmartLifecycle(LifecycleManager lifecycleManager) {
        this.lifecycleManager = lifecycleManager;
    }

    @Override
    public void start() {
        if (logger.isDebugEnabled()) {
            logger.info("Lifecycle组件启动中");
        }

        try {
            lifecycleManager.startAll();
        } catch (ServerStopException stopException) {
            logger.error("component.start.err: [component:{}, msg:{}]", stopException.getLifecycle().getClass().getName(), stopException.getMessage());
            throw stopException;
        }
    }

    @Override
    public void stop() {
        if (logger.isDebugEnabled()) {
            logger.info("Lifecycle组件关闭中");
        }

        try {
            lifecycleManager.closeAll();
        } catch (ServerStopException stopException) {
            logger.error("component.close.err: [component:{}, msg:{}]", stopException.getLifecycle().getClass().getName(), stopException.getMessage());
            throw stopException;
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isAutoStartup() {
        return SmartLifecycle.super.isAutoStartup();
    }

}
