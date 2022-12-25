package com.smartapps.smartuser.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
	protected MessageService messageService;

	public static final String ROOTUSER = "superadmin";

	@Override
	public void run(String... args) throws Exception {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		if(!smartUserServiceFacade.isUserExist(ROOTUSER)) {
			smartUserServiceFacade.register(prepareRootUser());
			log.info("User '"+ ROOTUSER +"' Created successfully.");
		} else {
			log.info("User '"+ ROOTUSER +"' already exists.");
		}
		
	}
	
	private SmartUserDto prepareRootUser() {
		return SmartUserDto.builder()
				.name(ROOTUSER)
				.password("$2a$12$3p9rmHfTG0h.iDMGGDLr3ObYMUcwfR/r6Nl2.RggM4aKqFbByIk7G")
				.roles("SUPER_ADMIN")
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
