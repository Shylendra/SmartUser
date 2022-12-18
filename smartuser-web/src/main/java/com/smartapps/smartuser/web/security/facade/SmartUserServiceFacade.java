package com.smartapps.smartuser.web.security.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartuser.web.dto.SmartUserDto;

public interface SmartUserServiceFacade {

	public SmartUserDto register(final SmartUserDto obj) throws JsonProcessingException;
	public List<SmartUserDto> retrieveAll() throws JsonProcessingException;
	public SmartUserDto retrieveById(final Integer id);
	public List<SmartUserDto> retrieveByUserName(final String userName) throws JsonProcessingException;
	public List<SmartUserDto> retrieveByUserNameAndAppId(final String userName, final String appId) throws JsonProcessingException;
	public List<SmartUserDto> retrieveByAppId(final String appId) throws JsonProcessingException;
	public SmartUserDto update(final SmartUserDto obj) throws JsonProcessingException;
	public void deleteById(final Integer id);

}
