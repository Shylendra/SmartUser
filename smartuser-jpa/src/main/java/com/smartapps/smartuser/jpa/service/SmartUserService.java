package com.smartapps.smartuser.jpa.service;

import java.util.List;
import java.util.Optional;

import com.smartapps.smartuser.jpa.entities.SmartUser;

public interface SmartUserService {
	
	/* Create */
	public Optional<SmartUser> create(final SmartUser obj);
	
	/* Read */
	public List<SmartUser> readAll();
	public SmartUser readById(final String id);
	public SmartUser readByUserName(final String userName);
	public SmartUser readByUserNameAndAppId(final String userName, final String appId);
	public SmartUser readByIdAndEmailAndActivationTokenAndProcAppId(final String id, final String email, final String activationToken, final String appId);
	public List<SmartUser> readByAppId(final String appId);
	public List<SmartUser> readByAppIds(final List<String> appIds);
	public boolean isUserExist(final String userName);
	public boolean isUserExist(final String userName, final String appId);
	public boolean isUserActive(final String id, final String email, final String activationToken, final String appId);
	public boolean activeUser(final String id, final String email, final String activationToken, final String appId);
	
	/* Update */
	public Optional<SmartUser> update(final SmartUser obj);
	public void updateStatus(final String id, final String appId, final String status);
	public void updateStatus(final SmartUser obj, final String status);
	
	/* Delete */
	public void deleteById(final String id);
	public void delete(List<String> ids);

}
