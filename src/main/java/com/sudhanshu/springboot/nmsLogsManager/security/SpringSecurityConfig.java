package com.sudhanshu.springboot.nmsLogsManager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sudhanshu.springboot.nmsLogsManager.repository.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService ;
	
	@Autowired
	public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/*/nmsLogsManager/v1/**").hasRole("USER")
				.antMatchers("/*/nmsLogsManager/v1/**").hasRole("ADMIN")
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager())).addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsService));
				
	}

}
