package com.wingflare.lib.lifecycle;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.api.lifecycle.LifecycleStatus;
import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

/**
 * 组件生命周期管理器
 */
public class LifecycleManager {


    private static final Logger logger = LoggerFactory.getLogger(LifecycleManager.class);

    /**
     * 按顺序启动所有组件
     */
    public void startAll() {
        // 按启动顺序排序
        List<Lifecycle> sortedComponents = Container.getAll(Lifecycle.class).stream()
                .sorted(Comparator.comparingInt(Lifecycle::startSort))
                .toList();

        for (Lifecycle component : sortedComponents) {
            if (component.getStatus() == LifecycleStatus.UNRUN) {
                logger.info("启动组件: {} (排序: {})",
                        component.getClass().getSimpleName(),
                        component.startSort());
                component.start();
                logger.info("组件启动完成: {}", component.getClass().getSimpleName());
            }
        }
    }

    /**
     * 按顺序关闭所有组件
     */
    public void closeAll() {
        // 按关闭顺序排序（倒序，确保与启动顺序相反）
        List<Lifecycle> sortedComponents = Container.getAll(Lifecycle.class).stream()
                .sorted(Comparator.comparingInt(Lifecycle::closeSort))
                .toList();

        for (Lifecycle component : sortedComponents) {
            if (component.getStatus() == LifecycleStatus.RUNNING) {
                logger.info("关闭组件: {} (排序: {})",
                        component.getClass().getSimpleName(),
                        component.closeSort());
                component.close();
                logger.info("组件关闭完成: {}", component.getClass().getSimpleName());
            }
        }
    }

}
