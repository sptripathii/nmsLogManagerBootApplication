package com.sudhanshu.springboot.nmsLogsManager.exception;

@SuppressWarnings("serial")
public class NmsLogManagerExeption extends RuntimeException {

	public NmsLogManagerExeption() {
		super();
	}

	public NmsLogManagerExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NmsLogManagerExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public NmsLogManagerExeption(String message) {
		super(message);
	}

	public NmsLogManagerExeption(Throwable cause) {
		super(cause);
	}

}
