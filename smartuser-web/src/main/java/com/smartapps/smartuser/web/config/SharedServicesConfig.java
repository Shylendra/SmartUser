package com.smartapps.smartuser.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.smartapps.smartlib.config.MessageSourceConfig;
import com.smartapps.smartlib.service.ClientDetailsService;
import com.smartapps.smartlib.service.ClientDetailsServiceImpl;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.service.MessageServiceImpl;

@Configuration
@Import({MessageSourceConfig.class})
public class SharedServicesConfig {
	
	@Bean
	public ClientDetailsService clientDetailsService() {
		return new ClientDetailsServiceImpl();
	}

	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}

}
