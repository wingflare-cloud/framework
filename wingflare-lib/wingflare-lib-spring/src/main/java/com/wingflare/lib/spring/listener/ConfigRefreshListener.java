package com.wingflare.lib.spring.listener;

import com.wingflare.lib.spring.event.ConfigRefreshEvent;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2022/3/6}
 * @description
 */
@Service
public class ConfigRefreshListener implements Ordered {

    @Resource
    private Environment environment;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @EventListener(EnvironmentChangeEvent.class)
    public void listener(EnvironmentChangeEvent event) {
        for (String refreshKey : event.getKeys()) {
            Object afterRefreshed = environment.getProperty(refreshKey);
            eventPublisher.publishEvent(new ConfigRefreshEvent(this, refreshKey, afterRefreshed, afterRefreshed));
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE - 1;
    }

}
