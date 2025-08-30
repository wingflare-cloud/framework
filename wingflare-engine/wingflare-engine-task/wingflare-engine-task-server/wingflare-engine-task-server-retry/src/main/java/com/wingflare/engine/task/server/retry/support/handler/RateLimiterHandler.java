package com.wingflare.engine.task.server.retry.support.handler;

import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-20
 */
@Component
public class RateLimiterHandler implements InitializingBean {
    private final SystemProperties systemProperties;
    private RateLimiter rateLimiter;

    public RateLimiterHandler(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public boolean tryAcquire(int permits) {
        return rateLimiter.tryAcquire(permits, 500L, TimeUnit.MILLISECONDS);
    }


    public void refreshRate( ) {
        int maxDispatchCapacity = systemProperties.getMaxDispatchCapacity();
        if (maxDispatchCapacity == rateLimiter.getRate()) {
            return;
        }
        rateLimiter.setRate(maxDispatchCapacity);
    }

    public void refreshRate(int maxDispatchCapacity ) {
        if (maxDispatchCapacity == rateLimiter.getRate()) {
            return;
        }
        rateLimiter.setRate(maxDispatchCapacity);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rateLimiter  = RateLimiter.create(systemProperties.getMaxDispatchCapacity());
    }

}
