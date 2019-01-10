package com.sudhanshu.sprindata.nmsLogsManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sudhanshu.sprindata.nmsLogsManager.entities.UserEntity;

public interface NmsUserManagerRepos extends CrudRepository<UserEntity, Integer> {
	
	List<UserEntity> findByName(String name);
	Optional<UserEntity> findByUserid(int userId);
	
}

