package com.sudhanshu.springboot.nmsLogsManager.exception;

@SuppressWarnings("serial")
public class DuplicateUserException extends NmsLogManagerExeption {

	public DuplicateUserException() {
		super();
	}

	public DuplicateUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateUserException(String message) {
		super(message);
	}

	public DuplicateUserException(Throwable cause) {
		super(cause);
	}

}
