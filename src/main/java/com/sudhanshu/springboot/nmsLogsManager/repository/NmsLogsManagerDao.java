package com.sudhanshu.springboot.nmsLogsManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogCountEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.exception.EmptyDeviceException;

@Component
public class NmsLogsManagerDao {
	
	private static final String DEBUG = "DEBUG";

	private static final String INFO = "INFO";

	private static final String WARNING = "WARNING";

	private static final String ERROR = "ERROR";

	private static final String TIMESTAMP = "timestamp";

	private static final int MAX_RECORD_AT_A_TIME = 50;

	@Autowired
	NmsLogsManagerRepos logRepo;
	
	@Autowired
	NmsUserManagerRepos userRepo;
	
	public List<NmsLogsEntity> getAllLogs(){
		Iterable<NmsLogsEntity> findAll = logRepo.findAll();
		return (List<NmsLogsEntity>)findAll ;
		
	}
	
	public List<NmsLogsEntity> getAllLatestLogs(){
		Iterable<NmsLogsEntity> findAll = logRepo.findTop50ByOrderByTimestampDesc();
		return (List<NmsLogsEntity>)findAll ;
		
	}
	
	public List<NmsLogsEntity> getAllLatestLogsForDevicesByTimeStamp(List<String> deviceList, long timestamp, Pageable pageable){
		if(deviceList == null || deviceList.isEmpty()) {
			throw new EmptyDeviceException();
		}
		Pageable sortedByLatestTimeStamp = 
			PageRequest.of(pageable.getPageNumber(), MAX_RECORD_AT_A_TIME, Sort.by(TIMESTAMP).descending());
		return logRepo.findAllByIpaddressInAndTimestampLessThan(deviceList, timestamp, sortedByLatestTimeStamp);
		
	}
	
	public NmsLogCountEntity getAllCount(int timestamp) {
		NmsLogCountEntity countEntity = new NmsLogCountEntity();
		countEntity.setErrorCount(logRepo.countByType(ERROR));
		countEntity.setWarnCount(logRepo.countByType(WARNING));
		countEntity.setInfoCount(logRepo.countByType(INFO));
		countEntity.setDebugCount((long) (50*Math.random())); //Since debug is not set, setting some random number
		
		return countEntity;
		
	}
	
	public List<NmsLogsEntity> getAllLogByType(String type){
		return (List<NmsLogsEntity>) logRepo.findByType(type);
		
	}
	
	public List<NmsLogsEntity> getAllLogByIPAddress(String ipaddress){
		return (List<NmsLogsEntity>) logRepo.findByIpaddress(ipaddress);
		
	}

	
	public List<UserEntity> getAllUsers(){
		return (List<UserEntity>) userRepo.findAll();
		
	}
	
	
	public UserEntity updateUserInfo(UserEntity user) {
		UserEntity savedUser = userRepo.findByUserid(user.getUserid());
		if (savedUser != null) {
			savedUser.setUserName(user.getUserName());
			savedUser.setRefreshInterval(user.getRefreshInterval());
			savedUser.setPassword(user.getPassword());
			savedUser.setAccesstokenvalidity(user.getAccesstokenvalidity());
			savedUser.setRefreshtokenvalidity(user.getRefreshtokenvalidity());
			if(user.getDevices() != null && user.getDevices().isEmpty()) savedUser.setDevices(user.getDevices());
			userRepo.save(savedUser);
			return savedUser;
		}
		return user;
	}

	public UserEntity findUser(String username) {
		return userRepo.findByUsername(username);
	}
	
}
