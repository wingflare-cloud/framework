package com.wingflare.adapter.spring.common;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.api.lifecycle.ServerStopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * 优雅启停
 */
@Component
public class AppSmartLifecycle implements SmartLifecycle, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AppSmartLifecycle.class);

    private volatile boolean isRunning = false;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void start() {
        if (logger.isDebugEnabled()) {
            logger.info("Lifecycle组件启动中");
        }

        try {
            List<Lifecycle> sortedComponents =  getApplicationContext().getBeansOfType(Lifecycle.class)
                    .values()
                    .stream()
                    .sorted(Comparator.comparingInt(Lifecycle::startSort))
                    .toList();

            for (Lifecycle component : sortedComponents) {
                try {
                    component.start();

                    if (logger.isDebugEnabled()) {
                        logger.info("组件[{}]启动", component.getClass().getName());
                    }

                } catch (ServerStopException stopException) {
                    stopException.setLifecycle(component);
                    throw stopException;
                } catch (Throwable throwable) {
                    logger.warn("component.start.err [{}]({})", component.getClass().getName(),
                            throwable.getMessage());
                }
            }

            isRunning = true;
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
            List<Lifecycle> sortedComponents =  getApplicationContext().getBeansOfType(Lifecycle.class)
                    .values()
                    .stream()
                    .sorted(Comparator.comparingInt(Lifecycle::closeSort))
                    .toList();

            for (Lifecycle component : sortedComponents) {
                try {
                    component.close();

                    if (logger.isDebugEnabled()) {
                        logger.info("组件[{}]关闭", component.getClass().getName());
                    }
                } catch (ServerStopException stopException) {
                    stopException.setLifecycle(component);
                    throw stopException;
                } catch (Throwable throwable) {
                    logger.warn("component.close.err [{}]({})", component.getClass().getName(),
                            throwable.getMessage());
                }
            }

            isRunning = false;
        } catch (ServerStopException stopException) {
            logger.error("component.close.err: [component:{}, msg:{}]", stopException.getLifecycle().getClass().getName(), stopException.getMessage());
            throw stopException;
        }
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    private ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext属性未注入");
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

}
