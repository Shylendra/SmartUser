package com.smartapps.smartuser.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartapps.smartlib.dto.SmartUserContextDto;
import com.smartapps.smartlib.enums.UserStatusEnum;
import com.smartapps.smartuser.jpa.entities.SmartUser;
import com.smartapps.smartuser.jpa.service.SmartUserService;
import com.smartapps.smartuser.web.assembler.SmartUserAssembler;

@Service
public class SmartUserDetailsService implements UserDetailsService {

	@Autowired
	private SmartUserService smartUserService;
	
	@Autowired
	private SmartUserAssembler smartUserAssembler;
	
	private SmartUserContextDto userContext;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		SmartUser user = smartUserService.readByUserName(userName);
		this.userContext = null;
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + userName + " not found.");
		}
		
		this.userContext = smartUserAssembler.mapToSmartUserContext(user);
		
		/* Update User status */
		//smartUserService.updateStatus(user, UserStatusEnum.AVAILABLE.getKey());
		return new SmartUserDetails(user);
	}
	
	public Boolean isValidAppUser(String userName, String appId) {
		return smartUserService.isUserExist(userName, appId);
	}

	public SmartUserContextDto getUserContext() {
		return this.userContext;
	}

}
