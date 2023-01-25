package com.smartapps.smartuser.web.security.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartapps.smartlib.dto.SmartUserContextDto;

public class SmartSecurityUtil {
	
	public static SmartUserContextDto retrievepUserContext(final String appId) {
		SmartUserContextDto userContext = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails)principal;
			userContext = SmartUserContextDto.builder()
					.appId(StringUtils.isNotEmpty(appId)? appId : null)
					.name(userDetails.getUsername())
					.roles(userDetails.getAuthorities().toString()).build();
		}
		return userContext;
	}
	
}
