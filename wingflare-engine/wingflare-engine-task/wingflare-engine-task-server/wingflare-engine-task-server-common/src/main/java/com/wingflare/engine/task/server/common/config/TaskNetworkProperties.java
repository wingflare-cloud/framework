package com.wingflare.engine.task.server.common.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "task.network")
public class TaskNetworkProperties extends com.wingflare.engine.task.common.core.network.TaskNetworkProperties {
}
