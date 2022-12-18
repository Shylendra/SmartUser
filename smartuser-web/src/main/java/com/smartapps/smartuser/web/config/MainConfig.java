package com.smartapps.smartuser.web.config;

import javax.annotation.PostConstruct;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Aspect
@ComponentScan({"com.smartapps.smartuser.shared.*",
	"com.smartapps.smartuser.jpa.*",
	"com.smartapps.smartuser.web.*",
	"com.smartapps.smartlib.*"})
@EntityScan("com.smartapps.smartuser.jpa.entities")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.smartapps.smartuser.jpa.repository" })
public class MainConfig {

	private final Environment env;
	
	@Autowired
	public MainConfig(Environment env) {
		this.env = env;
	}
	
	@PostConstruct
	public void init() {
		log.info("Load MainConfig ***");
		String jndiPath = env.getProperty("spring.datasource.jndi-name");
		log.info("JNDI: " + jndiPath);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public static PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setMethodAccessLevel(AccessLevel.PUBLIC);
		return modelMapper;
	}
	
}
