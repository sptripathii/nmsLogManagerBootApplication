package com.sudhanshu.springboot.nmsLogsManager.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "OAUTH_CLIENTS_DETAILS")
public class UserEntity {

	@Id
	String id;
	
	int userid;
	
	int refreshinterval;

	String username;

	String password;

	int refreshtokenvalidity;

	int accesstokenvalidity;

	@ElementCollection(targetClass=String.class)
	List<String> devices;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRefreshtokenvalidity() {
		return refreshtokenvalidity;
	}

	public void setRefreshtokenvalidity(int refreshtokenvalidity) {
		this.refreshtokenvalidity = refreshtokenvalidity;
	}

	public int getAccesstokenvalidity() {
		return accesstokenvalidity;
	}

	public void setAccesstokenvalidity(int accesstokenvalidity) {
		this.accesstokenvalidity = accesstokenvalidity;
	}

	public List<String> getDevices() {
		return devices;
	}

	public void setDevices(List<String> devices) {
		this.devices = devices;
	}

	public int getRefreshInterval() {
		return refreshinterval;
	}

	public void setRefreshInterval(int refreshinterval) {
		this.refreshinterval = refreshinterval;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
