package com.smartapps.smartuser.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.codehaus.plexus.util.StringUtils;
import org.jboss.logging.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesDelete;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPut;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartuser.web.util.SmartUserWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@Validated
@RequestMapping(path = SmartUserWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class SmartUserController extends CommonController {

	@Operation(summary = SmartUserWebUtil.REGISTER_USER_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartUserWebUtil.REGISTER_USER)
	public ResponseEntity<SmartUserDto> register(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = true) String userGroups,
			@Parameter(name = "registerUser", description = "JSON with request object in and out", required = true) @Valid @RequestBody SmartUserDto user,
			HttpServletRequest request) 
			throws JsonProcessingException {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		user.setPassword(encode(user.getPassword()));
		user.setProcApprId(appId);
		user.setProcUserId(userId);
		user.setProcUserIpAddress(SmartHttpUtil.getIpAddress(request));
		return ResponseEntity.ok().body(smartUserServiceFacade.register(user));
	}

	@Operation(summary = SmartUserWebUtil.RETRIEVE_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.RETRIEVE_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveAll() 
			throws IOException {
		return ResponseEntity.ok().body(smartUserServiceFacade.retrieveAll());
	}

	@Operation(summary = SmartUserWebUtil.RETRIEVE_USER_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.RETRIEVE_USER)
	public ResponseEntity<SmartUserDto> retrieveById(
			@PathVariable("id") @Valid Integer id) {
		return ResponseEntity.ok().body(smartUserServiceFacade.retrieveById(id));
	}

	@Operation(summary = SmartUserWebUtil.RETRIEVE_NAME_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.RETRIEVE_NAME_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserName(
			@PathVariable("userName") @Valid String userName) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserServiceFacade.retrieveByUserName(userName));
	}

	@Operation(summary = SmartUserWebUtil.RETRIEVE_NAME_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.RETRIEVE_NAME_APPID_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserNameAndAppId(
			@PathVariable("userName") @Valid String userName,
			@PathVariable("appId") @Valid String appId) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserServiceFacade.retrieveByUserNameAndAppId(userName, appId));
	}

	@Operation(summary = SmartUserWebUtil.RETRIEVE_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.RETRIEVE_APPID_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveByAppId(
			@PathVariable("appId") @Valid String appId) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserServiceFacade.retrieveByAppId(appId));
	}

	@Operation(summary = SmartUserWebUtil.UPDATE_USER_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartUserWebUtil.UPDATE_USER)
	public ResponseEntity<SmartUserDto> update(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = true) String userGroups,
			@PathVariable("id") @Valid Integer id,
			@Parameter(name = "updateUser", description = "JSON with request object in and out", required = true) @Valid @RequestBody SmartUserDto user,
			HttpServletRequest request) 
			throws JsonProcessingException {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		user.setId(id);
		user.setProcApprId(appId);
		user.setProcUserId(userId);
		user.setProcUserIpAddress(SmartHttpUtil.getIpAddress(request));
		if(StringUtils.isNotEmpty(user.getPassword())) {
			user.setPassword(encode(user.getPassword()));
		}
		return ResponseEntity.ok().body(smartUserServiceFacade.update(user));
	}

	@Operation(summary = SmartUserWebUtil.DELETE_USER_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartUserWebUtil.DELETE_USER)
	public ResponseEntity<String> deleteById(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = true) String userGroups,			
			@PathVariable("id") @Valid Integer id) {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		smartUserServiceFacade.deleteById(id);
		return ResponseEntity.ok().body("DELETED");
	}
	
}
