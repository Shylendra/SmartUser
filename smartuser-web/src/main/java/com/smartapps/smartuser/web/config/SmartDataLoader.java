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
	public static final String TESTADMIN = "testadmin";//tadmin
	public static final String TESTUSER = "testuser";//tuser

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
		createUser(TESTADMIN, "tadmin", "ADMIN");
		
		//Create User
		createUser(TESTUSER, "tuser", "USER");
		
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
				.firstName(ROOTUSER + "FN")
				.middleName(ROOTUSER + "MN")
				.lastName(ROOTUSER + "LN")
				.gender("MALE")
				.dob("2022-12-20")
				.phone("")
				.email(ROOTUSER + "@email.com")
				.profilePhotoPath("")
				.active(true)
				//.procTs("")
				.procApprId("SUPER_ADMIN_APP")
				.procUserId("superuser")
				.procUserIpAddress("1.1.1.1")
				.procUserLatitude("1.1.1.1")
				.procUserLongitude("1.1.1.1").build();
	}

}
