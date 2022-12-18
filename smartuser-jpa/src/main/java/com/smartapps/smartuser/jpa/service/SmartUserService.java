package com.smartapps.smartuser.jpa.service;

import java.util.List;
import java.util.Optional;

import com.smartapps.smartuser.jpa.entities.SmartUser;

public interface SmartUserService {
	
	/* Create */
	public Optional<SmartUser> create(final SmartUser obj);
	
	/* Read */
	public List<SmartUser> readAll();
	public SmartUser readById(final Integer id);
	public List<SmartUser> readByUserName(final String userName);
	public List<SmartUser> readByUserNameAndAppId(final String userName, final String appId);
	public List<SmartUser> readByAppId(final String appId);
	
	/* Update */
	public Optional<SmartUser> update(final SmartUser obj);
	
	/* Delete */
	public void deleteById(final Integer id);

}
