package com.smartapps.smartuser.web.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import com.smartapps.smartlib.exception.UnauthorizedException;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartuser.web.security.config.JWTUtil;
import com.smartapps.smartuser.web.security.config.SmartSecurityUtil;
import com.smartapps.smartuser.web.security.config.SmartUserDetailsService;
import com.smartapps.smartuser.web.util.SmartUserWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
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
	public ResponseEntity<AuthResponseDto> authenticate(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestBody AuthRequestDto authRequest) 
			throws Exception {
		
		/* Authenticate */
        try {
            this.authenticate(authRequest.getUserName(), authRequest.getPassword());
        } catch (BadCredentialsException e) {
        	throw new UnauthorizedException("Incorrect username or password");
        }
        
        /* load UserDetails */
        final UserDetails userDetails = smartUserDetailsService.loadUserByUsername(authRequest.getUserName());
        if(!smartUserDetailsService.isValidAppUser(authRequest.getUserName(), appId)) {
            throw new UnauthorizedException("Invalid app user");
        }
        
        AuthResponseDto resp = new AuthResponseDto();
        resp.setJwtToken(jwtUtil.generateToken(userDetails));
        resp.setUserContext(smartUserDetailsService.getUserContext());
        
        /* Generate token & return */
		return ResponseEntity.ok().body(resp);
	}

	@Operation(summary = SmartUserWebUtil.AUTH_RETRIEVE_USER_CONTEXT_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartUserWebUtil.AUTH_RETRIEVE_USER_CONTEXT)
	public ResponseEntity<SmartUserContextDto> retrieveUserContext(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = false) String appId) {
		SmartUserContextDto userContext = SmartSecurityUtil.retrievepUserContext(appId);
		log.info("User context: ", userContext);
		return ResponseEntity.ok().body(userContext);
	}
	
	private void authenticate(String userName, String password) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,password)
        );
	}

}
