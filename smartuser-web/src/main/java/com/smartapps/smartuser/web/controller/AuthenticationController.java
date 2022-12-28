package com.smartapps.smartuser.web.controller;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.dto.AuthRequestDto;
import com.smartapps.smartlib.dto.AuthResponseDto;
import com.smartapps.smartlib.dto.SmartUserContextDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartuser.web.security.config.JWTUtil;
import com.smartapps.smartuser.web.security.config.SmartUserDetailsService;
import com.smartapps.smartuser.web.util.SmartUserWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping(path = SmartUserWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private SmartUserDetailsService smartUserDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
	@Operation(summary = SmartUserWebUtil.AUTH_CONTEXT_ROOT_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartUserWebUtil.AUTH_CONTEXT_ROOT)
	public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto authRequest) 
			throws Exception {
		
		/* Authenticate */
        try {
            this.authenticate(authRequest.getUserName(), authRequest.getPassword());
        } catch (BadCredentialsException e) {
        	log.error("INVALID_CREDENTIALS: Incorrect username or password", e);
            throw new Exception("INVALID_CREDENTIALS: Incorrect username or password", e);
        }
        
        /* load UserDetails */
        final UserDetails userDetails = smartUserDetailsService.loadUserByUsername(authRequest.getUserName());
        
        /* Generate token & return */
		return ResponseEntity.ok().body(new AuthResponseDto(jwtUtil.generateToken(userDetails)));
	}

	@Operation(summary = SmartUserWebUtil.AUTH_RETRIEVE_USER_CONTEXT_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.AUTH_RETRIEVE_USER_CONTEXT)
	public ResponseEntity<SmartUserContextDto> retrieveUserContext(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = false) String appId) {
		SmartUserContextDto userContext = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails)principal;
			userContext = SmartUserContextDto.builder()
					.appId(StringUtils.isNotEmpty(appId)? appId : null)
					.userName(userDetails.getUsername())
					.roles(userDetails.getAuthorities().toString()).build();
		}
		
		log.info("User context: ", userContext);
		return ResponseEntity.ok().body(userContext);
	}
	
	private void authenticate(String userName, String password) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,password)
        );
	}

}
