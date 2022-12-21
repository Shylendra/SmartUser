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

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.micrometer.core.instrument.util.StringUtils;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private SmartUserDetailsService smartUserDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;

    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_PREFIX = "Bearer ";
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorization = request.getHeader(AUTH_HEADER);
		String userName = null;
		String jwtToken = null;
		
        if(StringUtils.isNotEmpty(authorization) && authorization.startsWith(AUTH_HEADER_PREFIX)) {
        	jwtToken = authorization.substring(7);
        	try {
                userName = jwtUtil.extractUsername(jwtToken);
        	} catch(IllegalArgumentException e) {
        		System.out.println("ILLEGAL_ARGUMENT: Unable to get JWT token.");
        		throw e;
        	} catch(ExpiredJwtException e) {
        		System.out.println("EXPIRED_JWT: JWT token has expired.");
        		throw e;
        	} catch(MalformedJwtException e) {
        		System.out.println("MALFORMED_JWT: Invalid JWT");
        		throw e;
        	}
        } else {
        	System.out.println("INVALID_FORMAT: JWT token does not begin with Bearer.");
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
            	System.out.println("INVALID_TOKENT: Invalid JWT token.");
            }
        } else {
        	System.out.println("userName is null or context is not null.");
        }
        
        chain.doFilter(request, response);
	}

}
