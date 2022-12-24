package com.smartapps.smartuser.web.service.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartuser.jpa.service.SmartUserService;
import com.smartapps.smartuser.web.assembler.SmartUserAssembler;

public class CommonServiceFacade {

	@Autowired
	protected SmartUserService smartUserService;
	
	@Autowired
	protected MessageService messageService;
	
	@Autowired
	protected SmartUserAssembler assembler;

}
