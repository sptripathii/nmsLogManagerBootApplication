package com.sudhanshu.sprindata.nmsLogsManager;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;
import com.sudhanshu.springboot.nmsLogsManager.repository.NmsUserManagerRepos;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NmsLogsManagerApplicationTests {
	
	
	
	@Autowired
	NmsUserManagerRepos userRepo;

	@Test
	public void getAllUsers() {
		UserEntity entries = userRepo.findByUserid(1);
		if(entries != null) {
			
		}
	}

}

