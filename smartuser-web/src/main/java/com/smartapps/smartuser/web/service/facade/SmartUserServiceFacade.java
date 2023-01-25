package com.smartapps.smartuser.web.service.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.SmartUserDto;

public interface SmartUserServiceFacade {

	public SmartUserDto register(final SmartUserDto obj) throws JsonProcessingException;
	public List<SmartUserDto> retrieveAll() throws JsonProcessingException;
	public SmartUserDto retrieveById(final String id);
	public SmartUserDto retrieveByUserName(final String userName) throws JsonProcessingException;
	public SmartUserDto retrieveByUserNameAndAppId(final String userName, final String appId) throws JsonProcessingException;
	public boolean isUserExist(final String userName);
	public Boolean isUserExist(final String userName, final String appId);
	public List<SmartUserDto> retrieveByAppId(final String appId) throws JsonProcessingException;
	public List<SmartUserDto> retrieveByAppIds(final List<String> appIds) throws JsonProcessingException;
	public SmartUserDto update(final SmartUserDto obj) throws JsonProcessingException;
	public void deleteById(final String id);
	public void delete(List<String> ids);

}
