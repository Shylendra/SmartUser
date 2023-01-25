package com.smartapps.smartuser.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartuser.web.service.facade.SmartUserServiceFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SmartDataLoader implements CommandLineRunner {

	@Autowired
	protected SmartUserServiceFacade smartUserServiceFacade;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	protected MessageService messageService;

	public static final String ROOTUSER = "superadmin";//sadmin
	public static final String TESTADMIN = "admin";//admin
	public static final String TESTUSER = "user";//user
	public static final String APP_ID = "SMART_ADMIN";

	@Override
	public void run(String... args) throws Exception {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		//Create Super Admin
		createUser(ROOTUSER, "sadmin", "SUPER_ADMIN");
		
		//Create Admin
		createUser(TESTADMIN, "admin", "ADMIN");
		
		//Create User
		createUser(TESTUSER, "user", "USER");
		
	}
	
	private void createUser(String name, String pwd, String role) throws JsonProcessingException {
		
		if(!smartUserServiceFacade.isUserExist(name)) {
			smartUserServiceFacade.register(prepareUser(name, pwd, role));
			log.info("User '"+ name +"' Created successfully.");
		} else {
			log.info("User '"+ name +"' already exists.");
		}
		
	}
	
	private SmartUserDto prepareUser(String name, String pwd, String role) {
		return SmartUserDto.builder()
				.name(name)
				.password(passwordEncoder.encode(pwd))
				.roles(role)
				.firstName(name.toUpperCase() + "First")
				.middleName(name.toUpperCase() + "Middle")
				.lastName(name.toUpperCase() + "Last")
				.dob("2022-12-20")
				.email(name + "@email.com")
				.active(true)
				.procAppId(APP_ID)
				.procUserId(ROOTUSER)
				.build();
	}

}
