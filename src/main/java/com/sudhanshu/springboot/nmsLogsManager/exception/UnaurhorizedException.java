package com.sudhanshu.springboot.nmsLogsManager.exception;

@SuppressWarnings("serial")
public class UnaurhorizedException extends NmsLogManagerExeption {

	public UnaurhorizedException() {
		super();
	}

	public UnaurhorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnaurhorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnaurhorizedException(String message) {
		super(message);
	}

	public UnaurhorizedException(Throwable cause) {
		super(cause);
	}

}
