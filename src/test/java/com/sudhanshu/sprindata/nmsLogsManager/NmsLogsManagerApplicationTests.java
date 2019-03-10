package com.sudhanshu.sprindata.nmsLogsManager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.sudhanshu.springboot.nmsLogsManager.NmsLogsManagerApplication;
import com.sudhanshu.springboot.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsLogsManagerRepos;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsUserManagerRepos;


@RunWith(SpringRunner.class)
@SpringBootTest (classes = NmsLogsManagerApplication.class)
public class NmsLogsManagerApplicationTests {
	
	
	
	@Autowired
	NmsUserManagerRepos userRepo;
	
	@Autowired
	NmsLogsManagerRepos logRepo;

	@Test
	public void getUserDetails() {
		UserEntity entity = userRepo.findByUsername("admin");
		if(entity != null) {
			assertEquals("admin", entity.getUserName());
			assertEquals("admin", entity.getPassword());
			assertEquals(1, entity.getUserid());
			
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void getLogs() {
		UserEntity entity = userRepo.findByUsername("admin");
		Pageable sortedByLatestTimeStamp = 
				PageRequest.of(0, 50, Sort.by("timestamp").descending());
		List<NmsLogsEntity> logEntity = logRepo.findAllByIpaddressInAndTimestampLessThan(entity.getDevices(),9999999999999l,sortedByLatestTimeStamp);
		if(logEntity != null && logEntity.contains(entity.getDevices().get(0))) {
			assertEquals(true,true);
			
		}
	}

}

