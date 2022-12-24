package com.smartapps.smartuser.web.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smartapps.smartlib.exception.UnauthorizedException;
import com.smartapps.smartlib.util.SmartHttpUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private SmartUserDetailsService smartUserDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    public static final String ERROR_ILLEGAL_TOKEN = "Error validating access token: Illegal token argument";
    public static final String ERROR_EXPIRED_TOKEN = "Error validating access token: Token has expired";
    public static final String ERROR_INVALID_TOKEN = "Error validating access token: Invalid token";
    public static final String ERROR_INVALID_TOKENPREFIX = "Error validating access token: Invalid token prefix, token should begin with '%s'";
    public static final String ERROR_EMPTY_USERNAME = "UserName is null or context is not null.";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String userName = null;
		String jwtToken = SmartHttpUtil.getHeaderAuthorizationToken(request);
		
        if(StringUtils.isNotEmpty(jwtToken)) {
        	try {
                userName = jwtUtil.extractUsername(jwtToken);
        	} catch(IllegalArgumentException e) {
        		log.error(ERROR_ILLEGAL_TOKEN);
    			throw new UnauthorizedException(ERROR_ILLEGAL_TOKEN);
        	} catch(ExpiredJwtException e) {
        		log.error(ERROR_EXPIRED_TOKEN);
    			throw new UnauthorizedException(ERROR_EXPIRED_TOKEN);
        	} catch(MalformedJwtException e) {
        		log.error(ERROR_INVALID_TOKEN);
    			throw new UnauthorizedException(ERROR_INVALID_TOKEN);
        	}
        } else {
    		log.error(String.format(ERROR_INVALID_TOKENPREFIX, SmartHttpUtil.AUTH_HEADER_PREFIX));
			throw new UnauthorizedException(String.format(ERROR_INVALID_TOKENPREFIX, SmartHttpUtil.AUTH_HEADER_PREFIX));
        }
        
        if(StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
        	UserDetails userDetails = smartUserDetailsService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwtToken,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
        		log.error(ERROR_INVALID_TOKEN);
    			throw new UnauthorizedException(ERROR_INVALID_TOKEN);
            }
        } else {
        	System.out.println(ERROR_EMPTY_USERNAME);
        }
        
        chain.doFilter(request, response);
	}

}
