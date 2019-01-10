package com.sudhanshu.sprindata.nmsLogsManager.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sudhanshu.sprindata.nmsLogsManager.entities.NmsLogsEntity;
import com.sudhanshu.sprindata.nmsLogsManager.repository.NmsLogsManagerDao;

@RestController
public class NmsLogsManagerResource {
	
	@Autowired
	NmsLogsManagerDao logDao;
	
	//Fetching all logs
	// /nmslog/
	// CrossOrigin()
	@GetMapping("/nmslog/")
	public ResponseEntity<List<NmsLogsEntity>> getAllNmsLogs(){
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		return new ResponseEntity<List<NmsLogsEntity>>(logDao.getAllLatestLogs(),headers,HttpStatus.OK);
	
	}
	
	// /nmslog/{type}
	@CrossOrigin()
	@GetMapping("/nmslog/{type}")
	public Response getAllNmsLogsType(@PathVariable String type){
		List<NmsLogsEntity> logEntities = logDao.getAllLogByType(type);
		return Response.ok() //200
				.entity(logEntities)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET,OPTIONS")
				.allow("OPTIONS").build();
	}

}
