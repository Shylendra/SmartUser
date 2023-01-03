package com.smartapps.smartuser.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.SmartUserContextDto;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartuser.web.assembler.SmartUserAssembler;
import com.smartapps.smartuser.web.service.facade.SmartUserServiceFacade;

@Service
public class SmartUserDetailsService implements UserDetailsService {

	@Autowired
	private SmartUserServiceFacade smartUserServiceFacade;
	
	@Autowired
	private SmartUserAssembler smartUserAssembler;
	
	private SmartUserContextDto userContext;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		SmartUserDto user = null;
		this.userContext = null;
		try {
			user = smartUserServiceFacade.retrieveByUserName(userName);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + userName + " not found.");
		}
		
		this.userContext = smartUserAssembler.mapToSmartUserContext(user);
		return new SmartUserDetails(user);
	}

	public SmartUserContextDto getUserContext() {
		return this.userContext;
	}

}
