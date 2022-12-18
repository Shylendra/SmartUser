package com.smartapps.smartuser.web.config;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class EhCacheConfig {
	
	public static final String THIRTY_MINUTE_CACHE = "thirty-minute-cache";
	
	@PostConstruct
	public void init() {
		System.out.println("*** Load EhCacheConfig ***");
	}
}
