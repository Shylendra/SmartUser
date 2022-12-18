package com.smartapps.smartuser.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartlib.service.ClientDetailsService;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartuser.web.security.facade.SmartUserServiceFacade;

public class CommonController {

	@Autowired
	protected ClientDetailsService clientDetailsService;
	
	@Autowired
	protected SmartUserServiceFacade smartUserServiceFacade;

	@Autowired
	protected MessageService messageService;

}
