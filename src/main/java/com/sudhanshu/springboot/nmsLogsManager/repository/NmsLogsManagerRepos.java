package com.sudhanshu.springboot.nmsLogsManager.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogsEntity;

@Repository
public interface NmsLogsManagerRepos extends JpaRepository<NmsLogsEntity, Long> {

	List<NmsLogsEntity> findByType(String type);
	List<NmsLogsEntity> findByTimestamp(long timeStamp);
	List<NmsLogsEntity> findByIpaddress(String ipaddress);
	
	List<NmsLogsEntity> findTop50ByOrderByTimestampDesc();
	Page<NmsLogsEntity> findAllByTimestamp(Pageable pageable);
	Page<NmsLogsEntity> findAll(Pageable pageable);
	List<NmsLogsEntity> findAllByIpaddressInAndTimestampLessThan(List<String> deviceList,long timeStamp, Pageable  pageable);
	
	long countByType(String type);
}


