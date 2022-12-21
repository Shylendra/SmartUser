package com.smartapps.smartuser.web.util;

public class SmartUserWebUtil {

	/* Controller Settings */
	public static final String CONTEXT_ROOT = "/smartuser-api/api/";
	public static final String AUTH_CONTEXT_ROOT = "/authenticate/";

	public static final String REGISTER_USER_OPERATION = "Register user";
	public static final String REGISTER_USER = "users";
	
	public static final String RETRIEVE_USERS_OPERATION = "Retrieve users";
	public static final String RETRIEVE_USERS = "users";
	
	public static final String RETRIEVE_USER_OPERATION = "Retrieve user by id";
	public static final String RETRIEVE_USER = "users/{id}";
	
	public static final String RETRIEVE_NAME_USERS_OPERATION = "Retrieve users by userName";
	public static final String RETRIEVE_NAME_USERS = "users/name/{userName}";
	
	public static final String RETRIEVE_NAME_APPID_USERS_OPERATION = "Retrieve users by userName and appId";
	public static final String RETRIEVE_NAME_APPID_USERS = "users/name/{userName}/appid";
	
	public static final String RETRIEVE_APPID_USERS_OPERATION = "Retrieve users by appId";
	public static final String RETRIEVE_APPID_USERS = "users/appid";
	
	public static final String UPDATE_USER_OPERATION = "Update user";
	public static final String UPDATE_USER = "users/{id}";
	
	public static final String DELETE_USER_OPERATION = "Delete user by id";
	public static final String DELETE_USER = "users/{id}";

	/* OpenAPI Settings */
	public static final String TITLE = CONTEXT_ROOT.replaceAll("/api", "").replaceAll("/", "");
	public static final String DESCRIPTION = "SmartUser REST API Information";
	public static final String VERSION = "1.0.0";

	private SmartUserWebUtil() {
	}

}
