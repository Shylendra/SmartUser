package com.smartapps.smartuser.web.security.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartuser.jpa.entities.SmartUser;
import com.smartapps.smartuser.web.dto.SmartUserDto;

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

		SmartUser entityObj = SmartLibraryUtil.map(obj, SmartUser.class);
		
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
			entityObj.setDob(obj.getSqlDob());
		}
		if(StringUtils.isNotEmpty(obj.getPhone())) {
			entityObj.setPhone(obj.getPhone());
		}
		if(StringUtils.isNotEmpty(obj.getEmail())) {
			entityObj.setEmail(obj.getEmail());
		}
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		SmartUserDto response = SmartLibraryUtil.map(smartUserService.create(entityObj).get(), SmartUserDto.class);
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));

		return response;
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
			objList.add(SmartLibraryUtil.map(entityObj, SmartUserDto.class));
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

		SmartUserDto response = SmartLibraryUtil.map(smartUserService.readById(id), SmartUserDto.class);

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));
		return response;
	}

	@Override
	public List<SmartUserDto> retrieveByUserName(String userName) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<SmartUserDto> objList = new ArrayList<>();
		List<SmartUser> entityObjList = smartUserService.readByUserName(userName);
		for(SmartUser entityObj: entityObjList) {
			objList.add(SmartLibraryUtil.map(entityObj, SmartUserDto.class));
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
	public List<SmartUserDto> retrieveByUserNameAndAppId(String userName, String appId) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<SmartUserDto> objList = new ArrayList<>();
		List<SmartUser> entityObjList = smartUserService.readByUserNameAndAppId(userName, appId);
		for(SmartUser entityObj: entityObjList) {
			objList.add(SmartLibraryUtil.map(entityObj, SmartUserDto.class));
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
	public List<SmartUserDto> retrieveByAppId(String appId) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<SmartUserDto> objList = new ArrayList<>();
		List<SmartUser> entityObjList = smartUserService.readByAppId(appId);
		for(SmartUser entityObj: entityObjList) {
			objList.add(SmartLibraryUtil.map(entityObj, SmartUserDto.class));
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

		SmartUser entityObj = SmartLibraryUtil.map(obj, SmartUser.class);
		
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
			entityObj.setDob(obj.getSqlDob());
		}
		if(StringUtils.isNotEmpty(obj.getPhone())) {
			entityObj.setPhone(obj.getPhone());
		}
		if(StringUtils.isNotEmpty(obj.getEmail())) {
			entityObj.setEmail(obj.getEmail());
		}
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		SmartUserDto response = SmartLibraryUtil.map(smartUserService.update(entityObj).get(), SmartUserDto.class);
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));
		return response;
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

}
