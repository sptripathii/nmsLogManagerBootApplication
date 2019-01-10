package com.sudhanshu.sprindata.nmsLogsManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;

@Component
public class NmsLogsManagerDao {
	
	@Autowired
	NmsLogsManagerRepos logRepo;
	
	public List<NmsLogsEntity> getAllLogs(){
		Iterable<NmsLogsEntity> findAll = logRepo.findAll();
		return (List<NmsLogsEntity>)findAll ;
		
	}
	
	public List<NmsLogsEntity> getAllLatestLogs(){
		Iterable<NmsLogsEntity> findAll = logRepo.findTop50ByOrderByTimestampDesc();
		return (List<NmsLogsEntity>)findAll ;
		
	}
	
	
	public List<NmsLogsEntity> getAllLogByType(String type){
		return (List<NmsLogsEntity>) logRepo.findByType(type);
		
	}
	
	public List<NmsLogsEntity> getAllLogByIPAddress(String ipaddress){
		return (List<NmsLogsEntity>) logRepo.findByIpaddress(ipaddress);
		
	}

}
