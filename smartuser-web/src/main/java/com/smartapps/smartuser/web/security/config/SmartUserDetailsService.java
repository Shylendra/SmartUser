package com.smartapps.smartuser.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartuser.web.service.facade.SmartUserServiceFacade;

@Service
public class SmartUserDetailsService implements UserDetailsService {

	@Autowired
	private SmartUserServiceFacade smartUserServiceFacade;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		SmartUserDto user = null;
		try {
			user = smartUserServiceFacade.retrieveByUserName(userName);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + userName + " not found.");
		}
		
		return new SmartUserDetails(user);
	}

}
