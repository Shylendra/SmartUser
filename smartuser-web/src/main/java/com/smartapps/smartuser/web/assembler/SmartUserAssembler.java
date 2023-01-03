package com.smartapps.smartuser.web.assembler;

import java.util.ArrayList;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Component;

import com.smartapps.smartlib.dto.SmartUserContextDto;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartuser.jpa.entities.SmartUser;

@Component
public class SmartUserAssembler {

	public SmartUserDto mapToDto(SmartUser entityObj) {
		SmartUserDto obj = new SmartUserDto();
		
		obj.setId(entityObj.getId());
		if(StringUtils.isNotEmpty(entityObj.getName())) {
			obj.setName(entityObj.getName());
		}
		if(StringUtils.isNotEmpty(entityObj.getPassword())) {
			obj.setPassword(entityObj.getPassword());
		}
		if(StringUtils.isNotEmpty(entityObj.getRoles())) {
			obj.setRoles(entityObj.getRoles());
		}
		if(StringUtils.isNotEmpty(entityObj.getFirstName())) {
			obj.setFirstName(entityObj.getFirstName());
		}
		if(StringUtils.isNotEmpty(entityObj.getMiddleName())) {
			obj.setMiddleName(entityObj.getMiddleName());
		}
		if(StringUtils.isNotEmpty(entityObj.getLastName())) {
			obj.setLastName(entityObj.getLastName());
		}
		if(StringUtils.isNotEmpty(entityObj.getGender())) {
			obj.setGender(entityObj.getGender());
		}
		if(entityObj.getDob() != null) {
			obj.setDob(entityObj.getDob().toString());
		}
		if(StringUtils.isNotEmpty(entityObj.getPhone())) {
			obj.setPhone(entityObj.getPhone());
		}
		if(StringUtils.isNotEmpty(entityObj.getEmail())) {
			obj.setEmail(entityObj.getEmail());
		}
		if(StringUtils.isNotEmpty(entityObj.getProfilePhotoPath())) {
			obj.setProfilePhotoPath(entityObj.getProfilePhotoPath());
		}
		obj.setActive(entityObj.getActive());
		obj.setAddresses(new ArrayList<>());


		/* Base Entity */
		if(entityObj.getProcTs() != null) {
			obj.setProcTs(entityObj.getProcTs().toString());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcApprId())) {
			obj.setProcApprId(entityObj.getProcApprId());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserId())) {
			obj.setProcUserId(entityObj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserIpAddress())) {
			obj.setProcUserIpAddress(entityObj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserLatitude())) {
			obj.setProcUserLatitude(entityObj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserLongitude())) {
			obj.setProcUserLongitude(entityObj.getProcUserLongitude());
		}
		return obj;
	}

	public SmartUser mapToEntity(SmartUserDto obj) {
		SmartUser entityObj = new SmartUser();
		
		if(StringUtils.isNotEmpty(obj.getName())) {
			entityObj.setName(obj.getName());
		}
		if(StringUtils.isNotEmpty(obj.getPassword())) {
			entityObj.setPassword(obj.getPassword());
		}
		if(StringUtils.isNotEmpty(obj.getRoles())) {
			entityObj.setRoles(obj.getRoles());
		}
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
		if(StringUtils.isNotEmpty(obj.getProfilePhotoPath())) {
			entityObj.setProfilePhotoPath(obj.getProfilePhotoPath());
		}
		entityObj.setActive(obj.getActive());

		/* Base Entity */
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		if(StringUtils.isNotEmpty(obj.getProcApprId())) {
			entityObj.setProcApprId(obj.getProcApprId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserId())) {
			entityObj.setProcUserId(obj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserIpAddress())) {
			entityObj.setProcUserIpAddress(obj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLatitude())) {
			entityObj.setProcUserLatitude(obj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLongitude())) {
			entityObj.setProcUserLongitude(obj.getProcUserLongitude());
		}

		return entityObj;
	}

	public void mapToEntityForUpdate(SmartUser entityObj, SmartUserDto obj) {
		
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
		if(StringUtils.isNotEmpty(obj.getProfilePhotoPath())) {
			entityObj.setProfilePhotoPath(obj.getProfilePhotoPath());
		}
		entityObj.setActive(obj.getActive());

		/* Base Entity */
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		if(StringUtils.isNotEmpty(obj.getProcApprId())) {
			entityObj.setProcApprId(obj.getProcApprId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserId())) {
			entityObj.setProcUserId(obj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserIpAddress())) {
			entityObj.setProcUserIpAddress(obj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLatitude())) {
			entityObj.setProcUserLatitude(obj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLongitude())) {
			entityObj.setProcUserLongitude(obj.getProcUserLongitude());
		}
	}	
	
	public SmartUserContextDto mapToSmartUserContext(SmartUserDto obj) {
		SmartUserContextDto entityObj = new SmartUserContextDto();
		
		entityObj.setId(obj.getId());
		if(StringUtils.isNotEmpty(obj.getProcApprId())) {
			entityObj.setAppId(obj.getProcApprId());
		}
		if(StringUtils.isNotEmpty(obj.getName())) {
			entityObj.setName(obj.getName());
		}
		if(StringUtils.isNotEmpty(obj.getRoles())) {
			entityObj.setRoles(obj.getRoles());
		}
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
		if(StringUtils.isNotEmpty(obj.getDob())) {
			entityObj.setDob(obj.getDob());
		}
		if(StringUtils.isNotEmpty(obj.getPhone())) {
			entityObj.setPhone(obj.getPhone());
		}
		if(StringUtils.isNotEmpty(obj.getEmail())) {
			entityObj.setEmail(obj.getEmail());
		}
		if(StringUtils.isNotEmpty(obj.getProfilePhotoPath())) {
			entityObj.setProfilePhotoPath(obj.getProfilePhotoPath());
		}
		entityObj.setActive(obj.getActive());

		return entityObj;
	}
	
}
