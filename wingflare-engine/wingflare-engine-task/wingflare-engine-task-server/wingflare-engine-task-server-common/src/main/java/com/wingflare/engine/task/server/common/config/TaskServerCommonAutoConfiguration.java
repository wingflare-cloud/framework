package com.wingflare.engine.task.server.common.config;

import com.wingflare.engine.task.server.common.Schedule;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.register.ClientRegister;
import com.wingflare.engine.task.datasource.template.persistence.mapper.ServerNodeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * web访问模块
 *
 * @author: opensnail
 * @date : 2023-09-19 09:21
 * @since : 2.4.0
 */
@Configuration
@ComponentScan("com.wingflare.engine.task.server.common.*")
public class TaskServerCommonAutoConfiguration {

    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(4);
        scheduler.setThreadNamePrefix("wingflare-task-scheduled-thread-");
        return scheduler;
    }

    @Bean
    public TaskScheduler alarmExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(4);
        scheduler.setThreadNamePrefix("wingflare-task-alarm-thread-");
        return scheduler;
    }

    @DependsOn(value = ClientRegister.BEAN_NAME)
    @Bean
    public Schedule refreshNodeSchedule(ClientRegister clientRegister, ServerNodeMapper serverNodeMapper,
                                        InstanceManager instanceManager) {
        ClientRegister.RefreshNodeSchedule refreshNodeSchedule =
                clientRegister.newRefreshNodeSchedule(serverNodeMapper, instanceManager);
        clientRegister.setRefreshNodeSchedule(refreshNodeSchedule);
        return refreshNodeSchedule;
    }

    @Bean(ClientRegister.BEAN_NAME)
    public ClientRegister clientRegister() {
        return new ClientRegister();
    }


}
