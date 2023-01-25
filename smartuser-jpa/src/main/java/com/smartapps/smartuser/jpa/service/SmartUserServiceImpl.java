package com.smartapps.smartuser.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return repository.findAllByOrderByProcTsDesc();
	}

	@Override
	public SmartUser readById(String id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<SmartUser> entityObj = repository.findById(id);
		if(entityObj.isPresent()) {
			return entityObj.get();
		}
		
		return null;
	}

	@Override
	public SmartUser readByUserName(String userName) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByName(userName);
	}

	@Override
	public SmartUser readByUserNameAndAppId(String userName, String appId) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByNameAndProcAppId(userName, appId);
	}

	@Override
	public List<SmartUser> readByAppId(String appId) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByProcAppIdOrderByIdDesc(appId);
	}

	@Override
	public List<SmartUser> readByAppIds(List<String> appIds) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findByProcAppIdInOrderByIdDesc(appIds);
	}

	@Override
	public Optional<SmartUser> update(SmartUser obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return Optional.of(repository.save(obj));
	}

	@Override
	public void deleteById(String id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		repository.deleteById(readById(id).getId());
	}

	@Override
	public boolean isUserExist(String userName) {
		return repository.existsSmartUserByName(userName);
	}

	@Override
	public boolean isUserExist(String userName, String appId) {
		return repository.existsSmartUserByNameAndProcAppId(userName, appId);
	}

	@Override
	public void delete(List<String> ids) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		repository.deleteByIdIn(ids);
	}

}
