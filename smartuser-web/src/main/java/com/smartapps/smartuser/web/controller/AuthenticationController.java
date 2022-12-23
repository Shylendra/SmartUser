package com.smartapps.smartuser.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartuser.web.dto.AuthRequestDto;
import com.smartapps.smartuser.web.dto.AuthResponseDto;
import com.smartapps.smartuser.web.security.config.JWTUtil;
import com.smartapps.smartuser.web.security.config.SmartUserDetailsService;
import com.smartapps.smartuser.web.util.SmartUserWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@Validated
@RequestMapping(SmartUserWebUtil.CONTEXT_ROOT)
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
	
	private void authenticate(String userName, String password) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,password)
        );
	}

}
