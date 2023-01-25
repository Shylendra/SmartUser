package com.smartapps.smartuser.web.util;

public class SmartUserWebUtil {

	/* SmartUserController Settings */
	public static final String CONTEXT_ROOT = "/smartuser-api/";

	public static final String REGISTER_USER_OPERATION = "Register user";
	public static final String REGISTER_USER = "register";
	
	public static final String RETRIEVE_USERS_OPERATION = "Retrieve users";
	public static final String RETRIEVE_USERS = "api/users";
	
	public static final String RETRIEVE_USER_OPERATION = "Retrieve user by id";
	public static final String RETRIEVE_USER = "api/users/{id}";
	
	public static final String RETRIEVE_NAME_USERS_OPERATION = "Retrieve users by userName";
	public static final String RETRIEVE_NAME_USERS = "api/users/name/{userName}";
	
	public static final String RETRIEVE_NAME_APPID_USERS_OPERATION = "Retrieve users by userName and appId";
	public static final String RETRIEVE_NAME_APPID_USERS = "api/users/name/{userName}/appid/{appId}";
	
	public static final String RETRIEVE_NAME_APPID_USEREXISTS_OPERATION = "Check if user exists by userName and appId";
	public static final String RETRIEVE_NAME_APPID_USEREXISTS = "user-exists/name/{userName}/appid/{appId}";
	
	public static final String RETRIEVE_APPID_USERS_OPERATION = "Retrieve users by appId";
	public static final String RETRIEVE_APPID_USERS = "api/users/appid/{appId}";
	
	public static final String RETRIEVE_APPIDS_USERS_OPERATION = "Retrieve users by appIds";
	public static final String RETRIEVE_APPIDS_USERS = "api/users/appids";
	
	public static final String UPDATE_USER_OPERATION = "Update user";
	public static final String UPDATE_USER = "api/users/{id}";
	
	public static final String DELETE_USER_OPERATION = "Delete user by id";
	public static final String DELETE_USER = "api/users/{id}";
	
	public static final String DELETE_USER_INBULK_OPERATION = "Delete users by ids(bulk delete)";
	public static final String DELETE_USER_INBULK = "api/users/bulk-delete";

	/* AuthenticationController Settings */
	public static final String AUTH_CONTEXT_ROOT_OPERATION = "Authenticate user";
	public static final String AUTH_CONTEXT_ROOT = "authenticate/token";

	public static final String AUTH_RETRIEVE_USER_CONTEXT_OPERATION = "Authenticate user";
	public static final String AUTH_RETRIEVE_USER_CONTEXT = "api/users/context";

	private SmartUserWebUtil() {
	}

}
