package com.sudhanshu.springboot.nmsLogsManager.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "OAUTH_CLIENTS_DETAILS")
public class UserEntity {

	@Id
	String id;
	
	int userid;
	
	int refreshInterval;

	String username;

	String password;

	int refreshtokenvalidity;

	int accesstokenvalidity;

	String devices;

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

	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}

	public int getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(int refreshInterval) {
		this.refreshInterval = refreshInterval;
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
