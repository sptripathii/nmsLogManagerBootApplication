package com.sudhanshu.sprindata.nmsLogsManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;
import java.lang.String;

public interface NmsLogsManagerRepos extends CrudRepository<NmsLogsEntity, Long> {

	List<NmsLogsEntity> findByType(String type);
	List<NmsLogsEntity> findByTimestamp(long timeStamp);
	List<NmsLogsEntity> findByIpaddress(String ipaddress);
	
	List<NmsLogsEntity> findTop50ByOrderByTimestampDesc();
	
	long countByType(String type);
}


