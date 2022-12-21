package com.smartapps.smartuser.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@SpringBootApplication
@EnableEurekaClient
public class SmartuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartuserApplication.class, args);
	}

}
