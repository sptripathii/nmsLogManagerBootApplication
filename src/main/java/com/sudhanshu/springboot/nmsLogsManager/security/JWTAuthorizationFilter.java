
package com.sudhanshu.springboot.nmsLogsManager.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sudhanshu.springboot.nmsLogsManager.repository.CustomUserDetailsService;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	CustomUserDetailsService customUserDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService ) {
		super(authenticationManager);
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken userNamePasswordAuth = geAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuth);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken geAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstant.HEADER_STRING);
		if(token == null) return null;
		String username = Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token.replace(SecurityConstant.TOKEN_PREFIX, "")).getBody().getSubject();
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		return username!= null ? new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities()) : null;
	}

}
