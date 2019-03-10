package com.sudhanshu.springboot.nmsLogsManager.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sudhanshu.springboot.nmsLogsManager.exception.UnaurhorizedException;
import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogCountEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsLogsManagerDao;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nmsLogsManager/v1")
@SuppressWarnings("rawtypes")
public class NmsLogsManagerControlller {

	private static final String AUTH_ACCESS_TOKEN_IDENTIFIER = "x-auth-access-token";
	@Autowired
	NmsLogsManagerDao logDao;

	// Fetching all logs
	@SuppressWarnings("unchecked")
	@GetMapping("/syslog")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogs(@RequestHeader Map<String, Object> header,
			@RequestParam(value = "timestamp", defaultValue = "9999999999999") String timestamp,
			@PageableDefault(value = 50, page = 0) Pageable pageable) {
		DecodedJWT jwtDecodedToken = null;
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			jwtDecodedToken = JWTUtils.verifyHMACToken(token);
		} catch (TokenExpiredException te) {
			System.out.println("Token expired");
			ResponseEntity entity = new ResponseEntity(te.getMessage(), HttpStatus.UNAUTHORIZED);
			return entity;
		} catch (Exception e) {
			System.out.println("Unauthorized User");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		List<NmsLogsEntity> nmsLogEntityList = logDao.getAllLatestLogsForDevicesByTimeStamp(
				jwtDecodedToken.getClaim(JWTUtils.DEVICES_CLAIM).asList(String.class), Long.valueOf(timestamp),
				pageable);
		ResponseEntity entity = new ResponseEntity<List<NmsLogsEntity>>(nmsLogEntityList, HttpStatus.OK);
		return entity;

	}

	// Count all logs
	@SuppressWarnings("unchecked")
	@GetMapping("/syslog/count")
	//@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<NmsLogCountEntity> getAllNmsLogsCount(@RequestHeader Map<String, Object> header) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			JWTUtils.verifyHMACToken(token);
		} catch (TokenExpiredException te) {
			System.out.println("Token expired");
			ResponseEntity entity = new ResponseEntity(te.getMessage(), HttpStatus.UNAUTHORIZED);
			return entity;
		} catch (Exception e) {
			System.out.println("Unauthorized User");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		return new ResponseEntity<NmsLogCountEntity>(logDao.getAllCount(5), HttpStatus.OK);

	}

	// /nmslog/{type}
	@SuppressWarnings("unchecked")
	@GetMapping("/syslog/{type}")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogsType(@RequestHeader Map<String, Object> header,
			@PathVariable String type) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			JWTUtils.verifyHMACToken(token);
		} catch (TokenExpiredException te) {
			System.out.println("Token expired");
			ResponseEntity entity = new ResponseEntity(te.getMessage(), HttpStatus.UNAUTHORIZED);
			return entity;
		} catch (Exception e) {
			System.out.println("Unauthorized User");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		return new ResponseEntity<List<NmsLogsEntity>>(logDao.getAllLogByType(type), HttpStatus.OK);
	}

	// /nmslog/{type}
	@SuppressWarnings("unchecked")
	@GetMapping("/user/")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UserEntity>> getNmsUsers(@RequestHeader Map<String, Object> header) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			JWTUtils.verifyHMACToken(token);
		} catch (TokenExpiredException te) {
			System.out.println("Token expired");
			ResponseEntity entity = new ResponseEntity(te.getMessage(), HttpStatus.UNAUTHORIZED);
			return entity;
		} catch (Exception e) {
			System.out.println("Unauthorized User");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		return new ResponseEntity<List<UserEntity>>(logDao.getAllUsers(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/user/{userName}")
	public ResponseEntity<UserEntity> getNmsUser(@RequestHeader Map<String, Object> header,@PathVariable String userName) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			JWTUtils.verifyHMACToken(token);
		} catch (TokenExpiredException te) {
			System.out.println("Token expired");
			ResponseEntity entity = new ResponseEntity(te.getMessage(), HttpStatus.UNAUTHORIZED);
			return entity;
		} catch (Exception e) {
			System.out.println("Unauthorized User");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		return new ResponseEntity<UserEntity>(logDao.findUser(userName), HttpStatus.OK);
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<UserEntity> updateRefreshInterval(@PathVariable int userid,
			@RequestBody UserEntity userEntity) {
		UserEntity entity = logDao.updateUserInfo(userEntity);
		// URI location =
		// ServletUriComponentsBuilder.fromCurrentRequest().path("/{user}")
		// .buildAndExpand(entity.getUserName()).toUri();
		return new ResponseEntity<UserEntity>(entity, HttpStatus.ACCEPTED);

	}

	@PostMapping("/user/login")
	public ResponseEntity login(@RequestHeader(value = "Authorization") String authorization) {
		UserEntity userEntity = null;
		String[] userParam = null;
		try {
			userParam = getUserInformationFromAuth(authorization);
			userEntity = logDao.findUser(userParam[0]);
		} catch (Exception e) {
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;
		}
		if (userEntity != null) {
			String encryptedPassword = null;
			try {
				encryptedPassword = EncryptUtils.getInstance().encrypt(userParam[1]);
			} catch (Exception e) {
				ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
				return entity;
			}
			// Should use encrypted password if same is stored in DB
			if (userEntity.getPassword().equals(userParam[1])) {
				// Generate the token

				String token = JWTUtils.generateHMACToken(String.valueOf(userEntity.hashCode()),
						String.valueOf(userEntity.getUserid()),
						userEntity.getDevices().toArray(new String[userEntity.getDevices().size()]));
				HttpHeaders headers = new HttpHeaders();
				headers.add(AUTH_ACCESS_TOKEN_IDENTIFIER, token);
				headers.add("Access-Control-Expose-Headers",
						"devices,Date,x-auth-access-token,Server-Authorization,WWW-Authenticate");
				headers.addAll("devices", userEntity.getDevices());
				ResponseEntity entity = new ResponseEntity(headers, HttpStatus.OK);
				return entity;
			}

		}
		ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
		return entity;
	}

	@PutMapping("/user/")
	public ResponseEntity<UserEntity> updateUser(@RequestHeader Map<String, Object> header,
			@RequestBody UserEntity userEntity) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		DecodedJWT jwtDecodedToken = null;
		try {
			jwtDecodedToken = JWTUtils.verifyHMACToken(token);
		} catch (Exception e) {
			System.out.println("Unauthorized user");
			ResponseEntity<UserEntity> entity = new ResponseEntity<UserEntity>(userEntity, HttpStatus.UNAUTHORIZED);
			return entity;
		}
		UserEntity updatedUser = null;
		if (!jwtDecodedToken.getSubject().equals(String.valueOf(userEntity.getUserid()))) {
			throw new UnaurhorizedException(
					"User " + jwtDecodedToken.getId() + " not allowed to update info for " + userEntity.getUserid());
		}
		updatedUser = logDao.updateUserInfo(userEntity);
		ResponseEntity<UserEntity> entity = new ResponseEntity<UserEntity>(updatedUser, HttpStatus.CREATED);
		return entity;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/user/logout")
	public ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response,
			@RequestHeader Map<String, Object> header) {
		String token = (String) header.get(AUTH_ACCESS_TOKEN_IDENTIFIER);
		try {
			JWTUtils.verifyHMACToken(token);
		} catch (Exception e) {
			System.out.println("Unauthorized user");
			ResponseEntity entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
			return entity;

		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity("/user/generatetoken", HttpStatus.OK);
	}

	// Utility method to parse authorization information from authorization
	private String[] getUserInformationFromAuth(String authorization) {
		String[] userNamePassword = null;
		if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
			// Authorization: Basic base64credentials
			String base64Credentials = authorization.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			// credentials = username:password
			userNamePassword = credentials.split(":", 2);
		}
		return userNamePassword;
	}

}
