package com.smartapps.smartuser.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartapps.smartuser.web.util.SmartUserWebUtil;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title(SmartUserWebUtil.TITLE)
						.description(SmartUserWebUtil.DESCRIPTION)
						.version(SmartUserWebUtil.VERSION));
	}
	
}
