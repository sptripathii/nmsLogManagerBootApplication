package com.sudhanshu.springboot.nmsLogsManager.entities;

public class NmsLogCountEntity {
	
	/*
	 * {
	 * Error : 2, 
	 * Warning : 4,
	 * Info : 5,
	 * Debug : 10 
	 * }
	 * 
	 * */
	
	long errorCount;
	long warnCount;
	long infoCount;
	long debugCount;

	public long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

	public long getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(long warnCount) {
		this.warnCount = warnCount;
	}

	public long getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(long infoCount) {
		this.infoCount = infoCount;
	}

	public long getDebugCount() {
		return debugCount;
	}

	public void setDebugCount(long debugCount) {
		this.debugCount = debugCount;
	}

}
