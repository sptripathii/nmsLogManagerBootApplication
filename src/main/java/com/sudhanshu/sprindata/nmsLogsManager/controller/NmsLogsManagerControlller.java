package com.sudhanshu.sprindata.nmsLogsManager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogCountEntity;
import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.sprindata.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.sprindata.nmsLogsManager.repository.NmsLogsManagerDao;

@RestController
@RequestMapping("/nmsLogsManager/v1")
public class NmsLogsManagerControlller {

	@Autowired
	NmsLogsManagerDao logDao;

	// Fetching all logs
	@GetMapping("/syslog")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogs() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<List<NmsLogsEntity>>(logDao.getAllLatestLogs(), headers, HttpStatus.OK);

	}

	// Count all logs
	@GetMapping("/syslog/count")
	public ResponseEntity<NmsLogCountEntity> getAllNmsLogsCount() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<NmsLogCountEntity>(logDao.getAllCount(5), headers, HttpStatus.OK);

	}

	// /nmslog/{type}
	@GetMapping("/syslog/{type}")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogsType(@PathVariable String type) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<List<NmsLogsEntity>>(logDao.getAllLogByType(type), headers, HttpStatus.OK);
	}

	// /nmslog/{type}
	@GetMapping("/user/")
	public ResponseEntity<List<UserEntity>> getNmsUsers() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<List<UserEntity>>(logDao.getAllUsers(), headers, HttpStatus.OK);
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<UserEntity> updateRefreshInterval(@PathVariable int userid,
			@RequestBody UserEntity userEntity) {
		UserEntity entity = logDao.updateUserInfo(userEntity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{user}").buildAndExpand(entity.getName())
				.toUri();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<UserEntity>(entity, headers, HttpStatus.ACCEPTED);

	}

}
