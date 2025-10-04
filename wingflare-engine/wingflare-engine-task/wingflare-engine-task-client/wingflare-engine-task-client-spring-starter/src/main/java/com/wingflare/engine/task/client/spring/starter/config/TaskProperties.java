package com.wingflare.engine.task.client.spring.starter.config;


import com.wingflare.engine.task.common.core.config.ForyProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "task")
public class TaskProperties extends com.wingflare.engine.task.client.common.config.TaskProperties {

    @NestedConfigurationProperty
    protected ForyProperties fory = new ForyProperties();

    @Override
    public ForyProperties getFory() {
        return fory;
    }

    @Override
    public void setFory(ForyProperties fory) {
        this.fory = fory;
    }
}
