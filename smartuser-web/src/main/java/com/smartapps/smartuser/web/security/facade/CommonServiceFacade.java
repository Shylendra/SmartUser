package com.smartapps.smartuser.web.security.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartuser.jpa.service.SmartUserService;

public class CommonServiceFacade {

	@Autowired
	protected SmartUserService smartUserService;
	
	@Autowired
	protected MessageService messageService;

}
