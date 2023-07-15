package com.smartapps.smartuser.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SmartWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	//private SmartUserDetailsService userDetailsService;
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Autowired 
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    private static final String[] AUTH_WHITELIST = {
            // -- API public endpoints
            "/smartuser-api/authenticate/**",
            "/smartuser-api/register",
            "/smartuser-api/confirm",
            "/smartuser-api/user-exists/**",
            // -- Health check
            "/actuator/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };

    @Bean
    public AuthenticationProvider oauthProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setUserDetailsService(userDetailsService);
    	return provider;
    }

    /*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .cors().disable()
        .authorizeRequests()
        	.antMatchers(AUTH_WHITELIST).permitAll()// whitelist public UI resources
        	.antMatchers("/api/**").authenticated()// require authentication for any endpoint that's not whitelisted
        	.anyRequest().authenticated()
        	.and()
        	.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        	.and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
	
	
	/*
	@Bean
	public AuthenticationEntryPoint basicAuthEntryPoint() {
		return new BasicAuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException {
				JSONObject jsonObject = new JSONObject();
				try {
					response.addHeader("WWW-Authenticate", "Basic Realm - " + getRealmName());
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.getWriter()
							.println(jsonObject.put("exception", "HTTP Status 401 - " + authException.getMessage()));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterPropertiesSet() {
				setRealmName("smartapps");
				super.afterPropertiesSet();
			}
		};
	}
	*/
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
}
