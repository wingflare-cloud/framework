package com.wingflare.engine.task.server.ui.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnailJobAdminServerUiAutoConfiguration {

	@Bean
	public WebProperties webProperties() {
		WebProperties properties = new WebProperties();
		WebProperties.Resources resources = properties.getResources();
		resources.setStaticLocations(new String[]{"classpath:admin/"});
		return properties;
	}

}