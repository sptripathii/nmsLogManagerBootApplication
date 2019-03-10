package com.sudhanshu.springboot.nmsLogsManager.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsUserManagerRepos;

@Service(value = "userService")
@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	NmsUserManagerRepos userRepo;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByUsername(userName);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(user.getUserName());
		applicationUser.setPassword(user.getPassword());
		PasswordEncoder encoder =
			     PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), encoder.encode(applicationUser.getPassword()), getAuthority());
	}

	@SuppressWarnings("rawtypes")
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		List list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
}
