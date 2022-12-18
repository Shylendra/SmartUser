package com.smartapps.smartuser.jpa.service;

import java.util.List;
import java.util.Optional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartapps.smartlib.exception.ResourceNotFoundException;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartuser.jpa.entities.SmartUser;
import com.smartapps.smartuser.jpa.repository.SmartUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmartUserServiceImpl implements SmartUserService {

	@Autowired
	private SmartUserRepository repository;
	
	@Autowired
	private MessageService messageService;
	
	private static final String ENTITY_NAME = "Address";

	@Override
	public Optional<SmartUser> create(SmartUser obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return Optional.of(repository.save(obj));
	}

	@Override
	public List<SmartUser> readAll() {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findAll();
	}

	@Override
	public SmartUser readById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<SmartUser> entityObj = repository.findById(id);
		if(!entityObj.isPresent()) {
			throw new ResourceNotFoundException(messageService.getMessage(SharedMessages.ERR001_RESOURCE_NOTFOUND, 
					new Object[]{ENTITY_NAME,id}));
		}
		
		return entityObj.get();
	}

	@Override
	public List<SmartUser> readByUserName(String userName) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByName(userName);
	}

	@Override
	public List<SmartUser> readByUserNameAndAppId(String userName, String appId) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByNameAndProcApprId(userName, appId);
	}

	@Override
	public List<SmartUser> readByAppId(String appId) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByProcApprId(appId);
	}

	@Override
	public Optional<SmartUser> update(SmartUser obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		SmartUser entityObj = readById(obj.getId());
		
		if(StringUtils.isNotEmpty(obj.getFirstName())) {
			entityObj.setFirstName(obj.getFirstName());
		}
		if(StringUtils.isNotEmpty(obj.getMiddleName())) {
			entityObj.setMiddleName(obj.getMiddleName());
		}
		if(StringUtils.isNotEmpty(obj.getLastName())) {
			entityObj.setLastName(obj.getLastName());
		}
		if(StringUtils.isNotEmpty(obj.getGender())) {
			entityObj.setGender(obj.getGender());
		}
		if(obj.getDob() != null) {
			entityObj.setDob(obj.getDob());
		}
		if(StringUtils.isNotEmpty(obj.getPhone())) {
			entityObj.setPhone(obj.getPhone());
		}
		if(StringUtils.isNotEmpty(obj.getEmail())) {
			entityObj.setEmail(obj.getEmail());
		}
		
		return Optional.of(repository.save(entityObj));
	}

	@Override
	public void deleteById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		repository.deleteById(readById(id).getId());
	}

}
