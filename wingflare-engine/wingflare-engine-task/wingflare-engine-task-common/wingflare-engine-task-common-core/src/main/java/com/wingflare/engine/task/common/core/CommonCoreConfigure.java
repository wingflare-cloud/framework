package com.wingflare.engine.task.common.core;

import com.wingflare.engine.task.common.core.network.TaskNetworkProperties;
import com.wingflare.engine.task.common.core.util.SnailJobNetworkUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: opensnail
 * @date : 2021-11-25 10:52
 */
@Configuration
@ComponentScan("com.wingflare.engine.task.common.core.*")
public class CommonCoreConfigure {

    @Bean
    public SnailJobNetworkUtils snailJobNetworkUtils(TaskNetworkProperties taskNetworkProperties) {
        return new SnailJobNetworkUtils(taskNetworkProperties);
    }

}
