package com.sudhanshu.springboot.nmsLogsManager.entities;

import javax.persistence.*;

@Entity
@Table (name="syslogevents")
public class NmsLogsEntity {
	
	@Id
	long id;
	
	long timestamp;
	
	String type;
	
	String ipaddress;
	
	String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
