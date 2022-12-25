package com.smartapps.smartuser.web.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartuser.jpa.entities.SmartUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SmartUserServiceFacadeImpl extends CommonServiceFacade implements SmartUserServiceFacade {

	@Override
	public SmartUserDto register(SmartUserDto obj) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));

		SmartUser entityObj = smartUserService.readByUserNameAndAppId(obj.getName(), obj.getProcApprId());
		if(entityObj == null) {
			SmartUser reqEntityObj = assembler.mapToEntity(obj);
			SmartUser resEntityObj = smartUserService.create(reqEntityObj).get();
			SmartUserDto resObj = assembler.mapToDto(resEntityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							resObj}));
			return resObj;
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						"User '" + obj.getName() + "' with App ID '" +obj.getProcApprId() + "'already exists! "}));
		return null;
	}

	@Override
	public List<SmartUserDto> retrieveAll() throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<SmartUserDto> objList = new ArrayList<>();
		List<SmartUser> entityObjList = smartUserService.readAll();
		for(SmartUser entityObj: entityObjList) {
			objList.add(assembler.mapToDto(entityObj));
		}

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		
		return objList;
	}

	@Override
	public SmartUserDto retrieveById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		SmartUser entityObj = smartUserService.readById(id);
		if (entityObj != null) {
			SmartUserDto resObj = assembler.mapToDto(entityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							resObj}));
			return resObj;
		}
		return null;
	}

	@Override
	public SmartUserDto retrieveByUserName(String userName) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		SmartUser entityObj = smartUserService.readByUserName(userName);
		if(entityObj != null) {
			SmartUserDto resObj = assembler.mapToDto(entityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							SmartLibraryUtil.mapToString(resObj, true)}));
			return resObj;
		}
		
		return null;
	}

	@Override
	public SmartUserDto retrieveByUserNameAndAppId(String userName, String appId) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		SmartUser entityObj = smartUserService.readByUserNameAndAppId(userName, appId);
		if(entityObj != null) {
			SmartUserDto resObj = assembler.mapToDto(entityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							SmartLibraryUtil.mapToString(resObj, true)}));
			return resObj;
		}
		
		return null;
	}

	@Override
	public List<SmartUserDto> retrieveByAppId(String appId) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<SmartUserDto> objList = new ArrayList<>();
		List<SmartUser> entityObjList = smartUserService.readByAppId(appId);
		for(SmartUser entityObj: entityObjList) {
			objList.add(assembler.mapToDto(entityObj));
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		return objList;
	}

	@Override
	public SmartUserDto update(SmartUserDto obj) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));

		SmartUser entityObj = smartUserService.readByUserNameAndAppId(obj.getName(), obj.getProcApprId());
		if(entityObj != null) {
			assembler.mapToEntityForUpdate(entityObj, obj);
			SmartUser resEntityObj = smartUserService.update(entityObj).get();
			SmartUserDto resObj = assembler.mapToDto(resEntityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							resObj}));
			return resObj;
		}
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						"User '" + obj.getName() + "' with App ID '" +obj.getProcApprId() + "'doesn't exists! "}));

		return null;
	}

	@Override
	public void deleteById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		smartUserService.deleteById(id);
	}

	@Override
	public boolean isUserExist(String userName) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return smartUserService.isUserExist(userName);
	}

}
