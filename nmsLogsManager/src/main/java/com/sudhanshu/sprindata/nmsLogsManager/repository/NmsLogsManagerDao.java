package com.sudhanshu.sprindata.nmsLogsManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogCountEntity;
import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.sprindata.nmsLogsManager.entities.UserEntity;

@Component
public class NmsLogsManagerDao {
	
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
	
	public NmsLogCountEntity getAllCount(int timestamp) {
		NmsLogCountEntity countEntity = new NmsLogCountEntity();
		countEntity.setErrorCount(logRepo.countByType("ERROR"));
		countEntity.setWarnCount(logRepo.countByType("WARNING"));
		countEntity.setInfoCount(logRepo.countByType("INFO"));
		countEntity.setDebugCount(logRepo.countByType("DEBUG"));
		
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
		Optional<UserEntity> userEntity = userRepo.findByUserid(user.getUserid());
		if (userEntity.isPresent()) {
			UserEntity savedUser = userEntity.get();
			savedUser.setName(user.getName());
			savedUser.setRefreshInterval(user.getRefreshInterval());
			savedUser.setPasswd(user.getPasswd());
			userRepo.save(savedUser);
			return savedUser;
		}
		return user;
	}
	
}
