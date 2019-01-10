package com.sudhanshu.sprindata.nmsLogsManager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.sprindata.nmsLogsManager.repository.NmsLogsManagerRepos;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NmsLogsManagerApplicationTests {
	
	
	@Autowired 
	NmsLogsManagerRepos nmsRepo;

	@Test
	public void getAllLatestLogs() {
		List<NmsLogsEntity> records = nmsRepo.findTop50ByOrderByTimestampDesc();
		if(records != null ) {
			assertEquals(records.size(), 50);
		}
	}

}

