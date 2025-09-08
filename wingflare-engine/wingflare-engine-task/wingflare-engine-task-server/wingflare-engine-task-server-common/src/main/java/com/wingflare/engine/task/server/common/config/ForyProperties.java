package com.wingflare.engine.task.server.common.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(value = "task.fory")
public class ForyProperties extends com.wingflare.engine.task.common.core.config.ForyProperties {
}
