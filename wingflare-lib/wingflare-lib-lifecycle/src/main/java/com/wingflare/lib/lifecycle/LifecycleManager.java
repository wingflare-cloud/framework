package com.wingflare.lib.lifecycle;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.api.lifecycle.ServerStopException;
import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;


/**
 * 组件生命周期管理器
 */
public class LifecycleManager {

    private final Logger logger = LoggerFactory.getLogger(LifecycleManager.class);

    /**
     * 按顺序启动所有组件
     */
    public void startAll() throws ServerStopException {
        // 按启动顺序排序
        List<Lifecycle> sortedComponents = Container.getAll(Lifecycle.class).stream()
                .sorted(Comparator.comparingInt(Lifecycle::startSort))
                .toList();

        for (Lifecycle component : sortedComponents) {
            try {
                component.start();
            } catch (ServerStopException stopException) {
                stopException.setLifecycle(component);
                throw stopException;
            } catch (Throwable throwable) {
                logger.warn("component.start.err [{}]", component.getClass().getName());
            }
        }
    }

    /**
     * 按顺序关闭所有组件
     */
    public void closeAll() throws ServerStopException {
        // 按关闭顺序排序（倒序，确保与启动顺序相反）
        List<Lifecycle> sortedComponents = Container.getAll(Lifecycle.class).stream()
                .sorted(Comparator.comparingInt(Lifecycle::closeSort))
                .toList();

        for (Lifecycle component : sortedComponents) {
            try {
                component.close();
            } catch (ServerStopException stopException) {
                stopException.setLifecycle(component);
                throw stopException;
            } catch (Throwable throwable) {
                logger.warn("component.close.err [{}]", component.getClass().getName());
            }
        }
    }

}
