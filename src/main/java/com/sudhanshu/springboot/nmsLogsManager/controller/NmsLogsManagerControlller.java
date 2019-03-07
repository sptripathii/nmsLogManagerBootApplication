package com.sudhanshu.springboot.nmsLogsManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogCountEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsLogsManagerDao;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nmsLogsManager/v1")
public class NmsLogsManagerControlller {

	@Autowired
	NmsLogsManagerDao logDao;

	// Fetching all logs
	@GetMapping("/syslog")
	public ResponseEntity<Page<NmsLogsEntity>> getAllNmsLogs(@PageableDefault(value = 50, page = 0) Pageable pageable) {
		return new ResponseEntity<Page<NmsLogsEntity>>(logDao.getAllLatestLogsPageable(pageable),
				HttpStatus.OK);

	}

	// Count all logs
	@GetMapping("/syslog/count")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<NmsLogCountEntity> getAllNmsLogsCount() {
		return new ResponseEntity<NmsLogCountEntity>(logDao.getAllCount(5), HttpStatus.OK);

	}

	// /nmslog/{type}
	@GetMapping("/syslog/{type}")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogsType(@PathVariable String type) {
		return new ResponseEntity<List<NmsLogsEntity>>(logDao.getAllLogByType(type), HttpStatus.OK);
	}

	// /nmslog/{type}
	@GetMapping("/user/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UserEntity>> getNmsUsers() {
		return new ResponseEntity<List<UserEntity>>(logDao.getAllUsers(), HttpStatus.OK);
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<UserEntity> updateRefreshInterval(@PathVariable int userid,
			@RequestBody UserEntity userEntity) {
		UserEntity entity = logDao.updateUserInfo(userEntity);
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{user}")
		//		.buildAndExpand(entity.getUserName()).toUri();
		return new ResponseEntity<UserEntity>(entity, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/logmeout", method = RequestMethod.POST)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

}
