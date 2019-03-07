package com.sudhanshu.springboot.nmsLogsManager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sudhanshu.springboot.nmsLogsManager.entities.UserEntity;

@Repository
public interface NmsUserManagerRepos extends MongoRepository<UserEntity, String> {
	
	List<UserEntity> findByUsername(String username);
	UserEntity findByUserid(int userid);
	List<UserEntity> findAll();
}

